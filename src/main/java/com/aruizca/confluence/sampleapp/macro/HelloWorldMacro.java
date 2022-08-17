package com.aruizca.confluence.sampleapp.macro;

import com.atlassian.confluence.content.render.xhtml.ConversionContext;
import com.atlassian.confluence.macro.Macro;
import com.atlassian.confluence.macro.MacroExecutionException;

import java.util.Map;

public class HelloWorldMacro implements Macro {

    public String execute(Map<String, String> map, String s, ConversionContext conversionContext) throws MacroExecutionException {
        return "<h2>Hello World</h2>";
    }

    public BodyType getBodyType() {
        return BodyType.PLAIN_TEXT;
    }

    public OutputType getOutputType() {
        return OutputType.INLINE;
    }

}
