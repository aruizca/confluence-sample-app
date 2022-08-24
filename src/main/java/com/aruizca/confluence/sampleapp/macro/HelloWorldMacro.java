package com.aruizca.confluence.sampleapp.macro;

import com.aruizca.confluence.mainapp.api.ParentService;
import com.aruizca.confluence.sampleapp.util.OsgiUtils;
import com.atlassian.confluence.content.render.xhtml.ConversionContext;
import com.atlassian.confluence.macro.Macro;
import com.atlassian.confluence.macro.MacroExecutionException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

public class HelloWorldMacro implements Macro {

    ParentService parentService;

    @Autowired
    public HelloWorldMacro() {
    }

    public String execute(Map<String, String> map, String s, ConversionContext conversionContext) throws MacroExecutionException {
        String messageFromParent = getParentService() == null ? "NULL" : getParentService().getMessage();
        return "<h2>Hello World " + messageFromParent + " </h2>";
    }

    private ParentService getParentService() {
        return parentService == null ? OsgiUtils.getService(ParentService.class) : parentService;
    }

    public BodyType getBodyType() {
        return BodyType.PLAIN_TEXT;
    }

    public OutputType getOutputType() {
        return OutputType.INLINE;
    }

}
