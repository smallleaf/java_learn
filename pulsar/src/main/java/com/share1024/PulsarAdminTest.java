package com.share1024;

import org.apache.pulsar.client.admin.PulsarAdmin;
import org.apache.pulsar.client.admin.PulsarAdminException;
import org.apache.pulsar.client.api.PulsarClientException;
import org.apache.pulsar.common.policies.data.TenantInfoImpl;
import org.junit.Test;

import java.util.HashSet;
import java.util.List;

/**
 * @Description
 * @Date 2023年08月29日
 * @Created by yesheng
 */
public class PulsarAdminTest {

    @Test
    public void tenants() throws PulsarClientException, PulsarAdminException {
        PulsarAdmin pulsarAdmin = PulsarAdmin.builder().serviceHttpUrl("http://localhost:8080").build();
        List<String> clusters = pulsarAdmin.clusters().getClusters();
        System.out.println("clusters "+String.join(",",clusters));
        TenantInfoImpl tenantInfo = new TenantInfoImpl();
        tenantInfo.setAllowedClusters(new HashSet<>(clusters));
        pulsarAdmin.tenants().createTenant("test",tenantInfo);
    }
}
