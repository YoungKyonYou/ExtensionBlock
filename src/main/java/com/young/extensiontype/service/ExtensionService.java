package com.young.extensiontype.service;

import com.young.extensiontype.entity.Extension;

import java.util.List;

public interface ExtensionService {
    void addExtension(String ext);
    void deleteExtension(String ext);
    List<Extension> getCurrentExtension();
    List<String> getInitialExtension();
}
