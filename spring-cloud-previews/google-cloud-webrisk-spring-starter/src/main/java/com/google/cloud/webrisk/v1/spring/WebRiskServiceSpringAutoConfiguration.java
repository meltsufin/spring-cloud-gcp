/*
 * Copyright 2025 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.cloud.webrisk.v1.spring;

import com.google.api.core.BetaApi;
import com.google.api.gax.core.CredentialsProvider;
import com.google.api.gax.core.ExecutorProvider;
import com.google.api.gax.retrying.RetrySettings;
import com.google.api.gax.rpc.HeaderProvider;
import com.google.api.gax.rpc.TransportChannelProvider;
import com.google.cloud.spring.autoconfigure.core.GcpContextAutoConfiguration;
import com.google.cloud.spring.core.DefaultCredentialsProvider;
import com.google.cloud.spring.core.Retry;
import com.google.cloud.spring.core.util.RetryUtil;
import com.google.cloud.webrisk.v1.WebRiskServiceClient;
import com.google.cloud.webrisk.v1.WebRiskServiceSettings;
import java.io.IOException;
import java.util.Collections;
import javax.annotation.Generated;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

// AUTO-GENERATED DOCUMENTATION AND CLASS.
/**
 * Auto-configuration for {@link WebRiskServiceClient}.
 *
 * <p>Provides auto-configuration for Spring Boot
 *
 * <p>The default instance has everything set to sensible defaults:
 *
 * <ul>
 *   <li>The default transport provider is used.
 *   <li>Credentials are acquired automatically through Application Default Credentials.
 *   <li>Retries are configured for idempotent methods but not for non-idempotent methods.
 * </ul>
 */
@Generated("by google-cloud-spring-generator")
@BetaApi("Autogenerated Spring autoconfiguration is not yet stable")
@AutoConfiguration
@AutoConfigureAfter(GcpContextAutoConfiguration.class)
@ConditionalOnClass(WebRiskServiceClient.class)
@ConditionalOnProperty(
    value = "com.google.cloud.webrisk.v1.web-risk-service.enabled",
    matchIfMissing = true)
@EnableConfigurationProperties(WebRiskServiceSpringProperties.class)
public class WebRiskServiceSpringAutoConfiguration {
  private final WebRiskServiceSpringProperties clientProperties;
  private final CredentialsProvider credentialsProvider;
  private static final Log LOGGER = LogFactory.getLog(WebRiskServiceSpringAutoConfiguration.class);

  protected WebRiskServiceSpringAutoConfiguration(
      WebRiskServiceSpringProperties clientProperties, CredentialsProvider credentialsProvider)
      throws IOException {
    this.clientProperties = clientProperties;
    if (this.clientProperties.getCredentials().hasKey()) {
      if (LOGGER.isTraceEnabled()) {
        LOGGER.trace("Using credentials from WebRiskService-specific configuration");
      }
      this.credentialsProvider =
          ((CredentialsProvider) new DefaultCredentialsProvider(this.clientProperties));
    } else {
      this.credentialsProvider = credentialsProvider;
    }
  }

  /**
   * Provides a default transport channel provider bean, corresponding to the client library's
   * default transport channel provider. If the library supports both GRPC and REST transport, and
   * the useRest property is configured, the HTTP/JSON transport provider will be used instead of
   * GRPC.
   *
   * @return a default transport channel provider.
   */
  @Bean
  @ConditionalOnMissingBean(name = "defaultWebRiskServiceTransportChannelProvider")
  public TransportChannelProvider defaultWebRiskServiceTransportChannelProvider() {
    if (this.clientProperties.getUseRest()) {
      return WebRiskServiceSettings.defaultHttpJsonTransportProviderBuilder().build();
    }
    return WebRiskServiceSettings.defaultTransportChannelProvider();
  }

  /**
   * Provides a WebRiskServiceSettings bean configured to use a DefaultCredentialsProvider and the
   * client library's default transport channel provider
   * (defaultWebRiskServiceTransportChannelProvider()). It also configures the quota project ID and
   * executor thread count, if provided through properties.
   *
   * <p>Retry settings are also configured from service-level and method-level properties specified
   * in WebRiskServiceSpringProperties. Method-level properties will take precedence over
   * service-level properties if available, and client library defaults will be used if neither are
   * specified.
   *
   * @param defaultTransportChannelProvider TransportChannelProvider to use in the settings.
   * @return a {@link WebRiskServiceSettings} bean configured with {@link TransportChannelProvider}
   *     bean.
   */
  @Bean
  @ConditionalOnMissingBean
  public WebRiskServiceSettings webRiskServiceSettings(
      @Qualifier("defaultWebRiskServiceTransportChannelProvider")
          TransportChannelProvider defaultTransportChannelProvider)
      throws IOException {
    WebRiskServiceSettings.Builder clientSettingsBuilder;
    if (this.clientProperties.getUseRest()) {
      clientSettingsBuilder = WebRiskServiceSettings.newHttpJsonBuilder();
      if (LOGGER.isTraceEnabled()) {
        LOGGER.trace("Using REST (HTTP/JSON) transport.");
      }
    } else {
      clientSettingsBuilder = WebRiskServiceSettings.newBuilder();
    }
    clientSettingsBuilder
        .setCredentialsProvider(this.credentialsProvider)
        .setTransportChannelProvider(defaultTransportChannelProvider)
        .setEndpoint(WebRiskServiceSettings.getDefaultEndpoint())
        .setHeaderProvider(this.userAgentHeaderProvider());
    if (this.clientProperties.getQuotaProjectId() != null) {
      clientSettingsBuilder.setQuotaProjectId(this.clientProperties.getQuotaProjectId());
      if (LOGGER.isTraceEnabled()) {
        LOGGER.trace(
            "Quota project id set to "
                + this.clientProperties.getQuotaProjectId()
                + ", this overrides project id from credentials.");
      }
    }
    if (this.clientProperties.getExecutorThreadCount() != null) {
      ExecutorProvider executorProvider =
          WebRiskServiceSettings.defaultExecutorProviderBuilder()
              .setExecutorThreadCount(this.clientProperties.getExecutorThreadCount())
              .build();
      clientSettingsBuilder.setBackgroundExecutorProvider(executorProvider);
      if (LOGGER.isTraceEnabled()) {
        LOGGER.trace(
            "Background executor thread count is "
                + this.clientProperties.getExecutorThreadCount());
      }
    }
    Retry serviceRetry = clientProperties.getRetry();
    if (serviceRetry != null) {
      RetrySettings computeThreatListDiffRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.computeThreatListDiffSettings().getRetrySettings(),
              serviceRetry);
      clientSettingsBuilder
          .computeThreatListDiffSettings()
          .setRetrySettings(computeThreatListDiffRetrySettings);

      RetrySettings searchUrisRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.searchUrisSettings().getRetrySettings(), serviceRetry);
      clientSettingsBuilder.searchUrisSettings().setRetrySettings(searchUrisRetrySettings);

      RetrySettings searchHashesRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.searchHashesSettings().getRetrySettings(), serviceRetry);
      clientSettingsBuilder.searchHashesSettings().setRetrySettings(searchHashesRetrySettings);

      RetrySettings createSubmissionRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.createSubmissionSettings().getRetrySettings(), serviceRetry);
      clientSettingsBuilder
          .createSubmissionSettings()
          .setRetrySettings(createSubmissionRetrySettings);

      if (LOGGER.isTraceEnabled()) {
        LOGGER.trace("Configured service-level retry settings from properties.");
      }
    }
    Retry computeThreatListDiffRetry = clientProperties.getComputeThreatListDiffRetry();
    if (computeThreatListDiffRetry != null) {
      RetrySettings computeThreatListDiffRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.computeThreatListDiffSettings().getRetrySettings(),
              computeThreatListDiffRetry);
      clientSettingsBuilder
          .computeThreatListDiffSettings()
          .setRetrySettings(computeThreatListDiffRetrySettings);
      if (LOGGER.isTraceEnabled()) {
        LOGGER.trace(
            "Configured method-level retry settings for computeThreatListDiff from properties.");
      }
    }
    Retry searchUrisRetry = clientProperties.getSearchUrisRetry();
    if (searchUrisRetry != null) {
      RetrySettings searchUrisRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.searchUrisSettings().getRetrySettings(), searchUrisRetry);
      clientSettingsBuilder.searchUrisSettings().setRetrySettings(searchUrisRetrySettings);
      if (LOGGER.isTraceEnabled()) {
        LOGGER.trace("Configured method-level retry settings for searchUris from properties.");
      }
    }
    Retry searchHashesRetry = clientProperties.getSearchHashesRetry();
    if (searchHashesRetry != null) {
      RetrySettings searchHashesRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.searchHashesSettings().getRetrySettings(), searchHashesRetry);
      clientSettingsBuilder.searchHashesSettings().setRetrySettings(searchHashesRetrySettings);
      if (LOGGER.isTraceEnabled()) {
        LOGGER.trace("Configured method-level retry settings for searchHashes from properties.");
      }
    }
    Retry createSubmissionRetry = clientProperties.getCreateSubmissionRetry();
    if (createSubmissionRetry != null) {
      RetrySettings createSubmissionRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.createSubmissionSettings().getRetrySettings(),
              createSubmissionRetry);
      clientSettingsBuilder
          .createSubmissionSettings()
          .setRetrySettings(createSubmissionRetrySettings);
      if (LOGGER.isTraceEnabled()) {
        LOGGER.trace(
            "Configured method-level retry settings for createSubmission from properties.");
      }
    }
    return clientSettingsBuilder.build();
  }

  /**
   * Provides a WebRiskServiceClient bean configured with WebRiskServiceSettings.
   *
   * @param webRiskServiceSettings settings to configure an instance of client bean.
   * @return a {@link WebRiskServiceClient} bean configured with {@link WebRiskServiceSettings}
   */
  @Bean
  @ConditionalOnMissingBean
  public WebRiskServiceClient webRiskServiceClient(WebRiskServiceSettings webRiskServiceSettings)
      throws IOException {
    return WebRiskServiceClient.create(webRiskServiceSettings);
  }

  private HeaderProvider userAgentHeaderProvider() {
    String springLibrary = "spring-autogen-web-risk-service";
    String version = this.getClass().getPackage().getImplementationVersion();
    return () -> Collections.singletonMap("user-agent", springLibrary + "/" + version);
  }
}
