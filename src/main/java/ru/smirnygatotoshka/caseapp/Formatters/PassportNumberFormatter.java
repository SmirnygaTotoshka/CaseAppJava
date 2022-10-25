package ru.smirnygatotoshka.caseapp.Formatters;

import javafx.scene.control.TextFormatter;

import java.util.function.UnaryOperator;

public class PassportNumberFormatter  implements UnaryOperator<TextFormatter.Change> {

    @Override
    public TextFormatter.Change apply(TextFormatter.Change change) {
        if (change.isContentChange()) {
            handleBackspaceOverSpecialCharacter(change);
            if (change.getText().matches("[0-9]*")) {
                int originalNewTextLength = change.getControlNewText().length();
                change.setText(formatNumber(change.getControlNewText()));
                change.setRange(0, change.getControlText().length());
                int caretOffset = change.getControlNewText().length() - originalNewTextLength;
                change.setCaretPosition(change.getCaretPosition() + caretOffset);
                change.setAnchor(change.getAnchor() + caretOffset);
                return change;
            } else {
                return null;
            }
        }
        return change;
    }

    private void handleBackspaceOverSpecialCharacter(TextFormatter.Change change) {
        if (change.isDeleted() && (change.getSelection().getLength() == 0)) {
            if (!Character.isDigit(change.getControlText().charAt(change.getRangeStart()))) {
                if (change.getRangeStart() > 0) {
                    change.setRange(change.getRangeStart() - 1, change.getRangeEnd() - 1);
                }
            }
        }
    }

    public static String formatNumber(String numbers) {
        numbers = numbers.replaceAll("[^\\d]", "");
        numbers = numbers.substring(0, Math.min(10, numbers.length()));
        if (numbers.length() == 0) {
            return "";
        }
        return numbers.replaceFirst("(\\d{4})(\\d{1,6})", "$1 $2");
    }

    public static String removeSpecial(String input){
        StringBuilder builder = new StringBuilder(input);
        builder.deleteCharAt(4);
        return builder.toString();
    }
}
