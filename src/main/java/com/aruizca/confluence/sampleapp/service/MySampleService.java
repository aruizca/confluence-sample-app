package com.aruizca.confluence.sampleapp.service;

import com.aruizca.confluence.sampleapp.api.SampleService;
import com.atlassian.plugin.spring.scanner.annotation.export.ExportAsService;
import org.springframework.stereotype.Component;

@Component
@ExportAsService({SampleService.class})
public class MySampleService implements SampleService {
    public String getMessage() {
        return "Sample App Loaded.";
    }
}
