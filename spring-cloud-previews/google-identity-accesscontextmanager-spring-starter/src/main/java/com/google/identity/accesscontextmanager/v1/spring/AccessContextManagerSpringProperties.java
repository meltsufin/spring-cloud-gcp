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

package com.google.identity.accesscontextmanager.v1.spring;

import com.google.api.core.BetaApi;
import com.google.cloud.spring.core.Credentials;
import com.google.cloud.spring.core.CredentialsSupplier;
import com.google.cloud.spring.core.Retry;
import javax.annotation.Generated;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

// AUTO-GENERATED DOCUMENTATION AND CLASS.
/** Provides default property values for AccessContextManager client bean */
@Generated("by google-cloud-spring-generator")
@BetaApi("Autogenerated Spring autoconfiguration is not yet stable")
@ConfigurationProperties("com.google.identity.accesscontextmanager.v1.access-context-manager")
public class AccessContextManagerSpringProperties implements CredentialsSupplier {
  /** OAuth2 credentials to authenticate and authorize calls to Google Cloud Client Libraries. */
  @NestedConfigurationProperty
  private final Credentials credentials =
      new Credentials("https://www.googleapis.com/auth/cloud-platform");
  /** Quota project to use for billing. */
  private String quotaProjectId;
  /** Number of threads used for executors. */
  private Integer executorThreadCount;
  /** Allow override of default transport channel provider to use REST instead of gRPC. */
  private boolean useRest = false;
  /** Allow override of retry settings at service level, applying to all of its RPC methods. */
  @NestedConfigurationProperty private Retry retry;
  /**
   * Allow override of retry settings at method-level for listAccessPolicies. If defined, this takes
   * precedence over service-level retry configurations for that RPC method.
   */
  @NestedConfigurationProperty private Retry listAccessPoliciesRetry;
  /**
   * Allow override of retry settings at method-level for getAccessPolicy. If defined, this takes
   * precedence over service-level retry configurations for that RPC method.
   */
  @NestedConfigurationProperty private Retry getAccessPolicyRetry;
  /**
   * Allow override of retry settings at method-level for listAccessLevels. If defined, this takes
   * precedence over service-level retry configurations for that RPC method.
   */
  @NestedConfigurationProperty private Retry listAccessLevelsRetry;
  /**
   * Allow override of retry settings at method-level for getAccessLevel. If defined, this takes
   * precedence over service-level retry configurations for that RPC method.
   */
  @NestedConfigurationProperty private Retry getAccessLevelRetry;
  /**
   * Allow override of retry settings at method-level for listServicePerimeters. If defined, this
   * takes precedence over service-level retry configurations for that RPC method.
   */
  @NestedConfigurationProperty private Retry listServicePerimetersRetry;
  /**
   * Allow override of retry settings at method-level for getServicePerimeter. If defined, this
   * takes precedence over service-level retry configurations for that RPC method.
   */
  @NestedConfigurationProperty private Retry getServicePerimeterRetry;
  /**
   * Allow override of retry settings at method-level for listGcpUserAccessBindings. If defined,
   * this takes precedence over service-level retry configurations for that RPC method.
   */
  @NestedConfigurationProperty private Retry listGcpUserAccessBindingsRetry;
  /**
   * Allow override of retry settings at method-level for getGcpUserAccessBinding. If defined, this
   * takes precedence over service-level retry configurations for that RPC method.
   */
  @NestedConfigurationProperty private Retry getGcpUserAccessBindingRetry;
  /**
   * Allow override of retry settings at method-level for setIamPolicy. If defined, this takes
   * precedence over service-level retry configurations for that RPC method.
   */
  @NestedConfigurationProperty private Retry setIamPolicyRetry;
  /**
   * Allow override of retry settings at method-level for getIamPolicy. If defined, this takes
   * precedence over service-level retry configurations for that RPC method.
   */
  @NestedConfigurationProperty private Retry getIamPolicyRetry;
  /**
   * Allow override of retry settings at method-level for testIamPermissions. If defined, this takes
   * precedence over service-level retry configurations for that RPC method.
   */
  @NestedConfigurationProperty private Retry testIamPermissionsRetry;

  @Override
  public Credentials getCredentials() {
    return this.credentials;
  }

  public String getQuotaProjectId() {
    return this.quotaProjectId;
  }

  public void setQuotaProjectId(String quotaProjectId) {
    this.quotaProjectId = quotaProjectId;
  }

  public boolean getUseRest() {
    return this.useRest;
  }

  public void setUseRest(boolean useRest) {
    this.useRest = useRest;
  }

  public Integer getExecutorThreadCount() {
    return this.executorThreadCount;
  }

  public void setExecutorThreadCount(Integer executorThreadCount) {
    this.executorThreadCount = executorThreadCount;
  }

  public Retry getRetry() {
    return this.retry;
  }

  public void setRetry(Retry retry) {
    this.retry = retry;
  }

  public Retry getListAccessPoliciesRetry() {
    return this.listAccessPoliciesRetry;
  }

  public void setListAccessPoliciesRetry(Retry listAccessPoliciesRetry) {
    this.listAccessPoliciesRetry = listAccessPoliciesRetry;
  }

  public Retry getGetAccessPolicyRetry() {
    return this.getAccessPolicyRetry;
  }

  public void setGetAccessPolicyRetry(Retry getAccessPolicyRetry) {
    this.getAccessPolicyRetry = getAccessPolicyRetry;
  }

  public Retry getListAccessLevelsRetry() {
    return this.listAccessLevelsRetry;
  }

  public void setListAccessLevelsRetry(Retry listAccessLevelsRetry) {
    this.listAccessLevelsRetry = listAccessLevelsRetry;
  }

  public Retry getGetAccessLevelRetry() {
    return this.getAccessLevelRetry;
  }

  public void setGetAccessLevelRetry(Retry getAccessLevelRetry) {
    this.getAccessLevelRetry = getAccessLevelRetry;
  }

  public Retry getListServicePerimetersRetry() {
    return this.listServicePerimetersRetry;
  }

  public void setListServicePerimetersRetry(Retry listServicePerimetersRetry) {
    this.listServicePerimetersRetry = listServicePerimetersRetry;
  }

  public Retry getGetServicePerimeterRetry() {
    return this.getServicePerimeterRetry;
  }

  public void setGetServicePerimeterRetry(Retry getServicePerimeterRetry) {
    this.getServicePerimeterRetry = getServicePerimeterRetry;
  }

  public Retry getListGcpUserAccessBindingsRetry() {
    return this.listGcpUserAccessBindingsRetry;
  }

  public void setListGcpUserAccessBindingsRetry(Retry listGcpUserAccessBindingsRetry) {
    this.listGcpUserAccessBindingsRetry = listGcpUserAccessBindingsRetry;
  }

  public Retry getGetGcpUserAccessBindingRetry() {
    return this.getGcpUserAccessBindingRetry;
  }

  public void setGetGcpUserAccessBindingRetry(Retry getGcpUserAccessBindingRetry) {
    this.getGcpUserAccessBindingRetry = getGcpUserAccessBindingRetry;
  }

  public Retry getSetIamPolicyRetry() {
    return this.setIamPolicyRetry;
  }

  public void setSetIamPolicyRetry(Retry setIamPolicyRetry) {
    this.setIamPolicyRetry = setIamPolicyRetry;
  }

  public Retry getGetIamPolicyRetry() {
    return this.getIamPolicyRetry;
  }

  public void setGetIamPolicyRetry(Retry getIamPolicyRetry) {
    this.getIamPolicyRetry = getIamPolicyRetry;
  }

  public Retry getTestIamPermissionsRetry() {
    return this.testIamPermissionsRetry;
  }

  public void setTestIamPermissionsRetry(Retry testIamPermissionsRetry) {
    this.testIamPermissionsRetry = testIamPermissionsRetry;
  }
}
