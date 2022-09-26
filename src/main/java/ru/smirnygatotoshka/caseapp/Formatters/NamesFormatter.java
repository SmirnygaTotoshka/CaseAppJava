package ru.smirnygatotoshka.caseapp.Formatters;

import javafx.scene.control.TextFormatter;

import java.util.Locale;
import java.util.function.UnaryOperator;

public class NamesFormatter implements UnaryOperator<TextFormatter.Change>{
//TODO formatter adequate
        @Override
        public TextFormatter.Change apply(TextFormatter.Change change) {
        if (change.isContentChange()) {
            if (change.getText().matches("[А-Яа-я]*")) {
                int originalNewTextLength = change.getControlNewText().length();
                change.setText(format(change.getControlNewText()));
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

    private String format(String old){
        old = old.replaceAll("[^А-Яа-я]","");
        old = old.substring(0, Math.min(50, old.length()));
        old = old.toLowerCase(Locale.ROOT);
        if (old.length() == 0)
            return "";
        return old.replaceFirst("[А-Яа-я]",old.substring(0,1).toUpperCase(Locale.ROOT));
    }
}
