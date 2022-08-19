package com.aruizca.confluence.sampleapp.macro;

import com.aruizca.confluence.mainapp.api.OtherService;
import com.aruizca.confluence.sampleapp.util.OsgiUtils;
import com.atlassian.confluence.content.render.xhtml.ConversionContext;
import com.atlassian.confluence.macro.Macro;
import com.atlassian.confluence.macro.MacroExecutionException;
import com.atlassian.plugin.spring.scanner.annotation.imports.ComponentImport;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

public class HelloWorldMacro implements Macro {

    OtherService otherService;

    @Autowired
    public HelloWorldMacro(OtherService otherService) {
        this.otherService = otherService;
    }

    public String execute(Map<String, String> map, String s, ConversionContext conversionContext) throws MacroExecutionException {
        return "<h2>Hello World" + otherService.getMessage() +"</h2>";
    }

    public BodyType getBodyType() {
        return BodyType.PLAIN_TEXT;
    }

    public OutputType getOutputType() {
        return OutputType.INLINE;
    }

}
