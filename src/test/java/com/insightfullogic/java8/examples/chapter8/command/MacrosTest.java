package com.insightfullogic.java8.examples.chapter8.command;

import org.junit.Test;

public class MacrosTest {

    @Test
    public void classBasedCommand() {
        MockEditor editor = new MockEditor();

        Macro macro = new Macro();
        macro.record(new Open(editor));
        macro.record(new Save(editor));
        macro.record(new Close(editor));
        macro.run();

        editor.check();
    }

    @Test
    public void lambdaBasedCommand() {
        MockEditor editor = new MockEditor();

        //note: 람다식을 이용하면 클래스 없이도 간단하게 사용할 수 있음 (Save, Save, Close)
        Macro macro = new Macro();
        macro.record(() -> editor.open());
        macro.record(() -> editor.save());
        macro.record(() -> editor.close());
        macro.run();

        editor.check();
    }

    @Test
    public void referenceBasedCommand() {
        MockEditor editor = new MockEditor();

        Macro macro = new Macro();
        macro.record(editor::open);
        macro.record(editor::save);
        macro.record(editor::close);
        macro.run();

        editor.check();
    }

}
