package ru.smirnygatotoshka.caseapp.Formatters;

import javafx.scene.control.TextFormatter;

import java.util.function.UnaryOperator;

public class PhoneNumberFilter implements UnaryOperator<TextFormatter.Change> {

    @Override
    public TextFormatter.Change apply(TextFormatter.Change change) {
        if (change.isContentChange()) {
            handleBackspaceOverSpecialCharacter(change);
            if (change.getText().matches("[0-9]*")) {
                int originalNewTextLength = change.getControlNewText().length();
                String f = formatNumber(change.getControlNewText());
                change.setText(f);//start with 7
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
                if (change.getRangeStart() > 0) {//start with 7
                    change.setRange(change.getRangeStart() - 1, change.getRangeEnd() - 1);
                }
            }
        }
    }
    public static String removeSpecial(String input){
        return input.replaceAll("\\D", "");
    }

    public static String formatNumber(String numbers) {
        numbers = numbers.replaceAll("\\D", "");
        numbers = numbers.replaceAll("^7","");
        numbers = numbers.substring(0,Math.min(10, numbers.length()));

        if (numbers.length() == 0) {
            return "";
        }
        if (numbers.length() < 4) {
            return "7(" + numbers;
        }
        if (numbers.length() < 7) {
            return numbers.replaceFirst("(\\d{3})(\\d+)", "7($1)$2");
        }
        return numbers.replaceFirst("(\\d{3})(\\d{3})(\\d+)","7($1)$2-$3");//TODO start with 7
    }
}