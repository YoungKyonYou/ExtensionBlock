package com.young.extensiontype.repository;

import com.young.extensiontype.entity.Extension;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExtensionRepository extends JpaRepository<Extension, String> {
}
