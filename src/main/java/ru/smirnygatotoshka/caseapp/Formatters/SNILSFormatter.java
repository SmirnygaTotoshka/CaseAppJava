package ru.smirnygatotoshka.caseapp.Formatters;

import javafx.scene.control.TextFormatter;

import java.util.function.UnaryOperator;

public class SNILSFormatter  implements UnaryOperator<TextFormatter.Change> {

    @Override
    public TextFormatter.Change apply(TextFormatter.Change change) {
        if (change.isContentChange()) {
            handleBackspaceOverSpecialCharacter(change);
            if (change.getText().matches("[0-9]*")) {
                int originalNewTextLength = change.getControlNewText().length();
                change.setText(formatSNILS(change.getControlNewText()));
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

    private String formatSNILS(String numbers) {

        numbers = numbers.replaceAll("[^\\d]", "");
        numbers = numbers.substring(0, Math.min(11, numbers.length()));
        StringBuilder builder = new StringBuilder(numbers);
        for (int i = 3; i < builder.length(); i+=4){
            builder.insert(i,"-");
        }

        return builder.toString();
    }

    public static String removeSpecial(String input){
        StringBuilder builder = new StringBuilder(input);
        for (int i = 3; i < builder.length(); i+=3){
            builder.deleteCharAt(i);
        }
        return builder.toString();
    }
}
