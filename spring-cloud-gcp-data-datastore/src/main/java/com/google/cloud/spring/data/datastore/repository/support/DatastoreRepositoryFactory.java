/*
 * Copyright 2017-2018 the original author or authors.
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

package com.google.cloud.spring.data.datastore.repository.support;

import com.google.cloud.spring.data.datastore.core.DatastoreOperations;
import com.google.cloud.spring.data.datastore.core.mapping.DatastoreMappingContext;
import com.google.cloud.spring.data.datastore.core.mapping.DatastorePersistentEntity;
import com.google.cloud.spring.data.datastore.core.mapping.DatastorePersistentEntityInformation;
import com.google.cloud.spring.data.datastore.repository.query.DatastoreQueryLookupStrategy;
import java.util.Optional;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.expression.BeanFactoryAccessor;
import org.springframework.context.expression.BeanFactoryResolver;
import org.springframework.data.mapping.MappingException;
import org.springframework.data.repository.core.EntityInformation;
import org.springframework.data.repository.core.RepositoryInformation;
import org.springframework.data.repository.core.RepositoryMetadata;
import org.springframework.data.repository.core.support.RepositoryFactorySupport;
import org.springframework.data.repository.query.Parameters;
import org.springframework.data.repository.query.QueryLookupStrategy;
import org.springframework.data.repository.query.QueryLookupStrategy.Key;
import org.springframework.data.repository.query.QueryMethodEvaluationContextProvider;
import org.springframework.data.repository.query.ValueExpressionDelegate;
import org.springframework.data.spel.EvaluationContextProvider;
import org.springframework.data.spel.ExpressionDependencies;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;

/**
 * Repository factory for Datastore.
 *
 * @since 1.1
 */
public class DatastoreRepositoryFactory extends RepositoryFactorySupport
    implements ApplicationContextAware {

  private final DatastoreMappingContext datastoreMappingContext;

  private final DatastoreOperations datastoreOperations;

  private ApplicationContext applicationContext;

  /**
   * Constructor.
   *
   * @param datastoreMappingContext the mapping context used to get mapping metadata for entity
   *     types.
   * @param datastoreOperations the Datastore operations object used by Datastore repositories.
   */
  DatastoreRepositoryFactory(
      DatastoreMappingContext datastoreMappingContext, DatastoreOperations datastoreOperations) {
    Assert.notNull(datastoreMappingContext, "A non-null Datastore mapping context is required.");
    Assert.notNull(datastoreOperations, "A non-null Datastore template object is required.");
    this.datastoreMappingContext = datastoreMappingContext;
    this.datastoreOperations = datastoreOperations;
  }

  @Override
  @SuppressWarnings("unchecked")
  public <T, I> EntityInformation<T, I> getEntityInformation(Class<T> domainClass) {
    DatastorePersistentEntity entity =
        this.datastoreMappingContext.getPersistentEntity(domainClass);

    if (entity == null) {
      throw new MappingException(
          "Could not lookup mapping metadata for domain class: " + domainClass.getName());
    }

    return new DatastorePersistentEntityInformation<>(entity);
  }

  @Override
  protected Object getTargetRepository(RepositoryInformation metadata) {
    return getTargetRepositoryViaReflection(
        metadata, this.datastoreOperations, metadata.getDomainType());
  }

  @Override
  protected Class<?> getRepositoryBaseClass(RepositoryMetadata metadata) {
    return SimpleDatastoreRepository.class;
  }

  @Override
  protected Optional<QueryLookupStrategy> getQueryLookupStrategy(
      @Nullable Key key, ValueExpressionDelegate valueExpressionDelegate) {

    return Optional.of(
        new DatastoreQueryLookupStrategy(
            this.datastoreMappingContext,
            this.datastoreOperations,
            valueExpressionDelegate));
  }

  /**
   * @deprecated in favor of {@link #getQueryLookupStrategy(Key, ValueExpressionDelegate)}
   */
  @Override
  @SuppressWarnings("deprecation")
  @Deprecated(since = "6.0")
  protected Optional<QueryLookupStrategy> getQueryLookupStrategy(
      @Nullable Key key, QueryMethodEvaluationContextProvider evaluationContextProvider) {

    return Optional.of(
        new DatastoreQueryLookupStrategy(
            this.datastoreMappingContext,
            this.datastoreOperations,
            delegateContextProvider(evaluationContextProvider)));

  }

  @Override
  public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
    this.applicationContext = applicationContext;
  }

  @SuppressWarnings("deprecation")
  private QueryMethodEvaluationContextProvider delegateContextProvider(
      QueryMethodEvaluationContextProvider evaluationContextProvider) {

    return new QueryMethodEvaluationContextProvider() {
      @Override
      public <T extends Parameters<?, ?>> EvaluationContext getEvaluationContext(
          T parameters, Object[] parameterValues) {
        StandardEvaluationContext evaluationContext =
            (StandardEvaluationContext)
                evaluationContextProvider.getEvaluationContext(parameters, parameterValues);
        evaluationContext.setRootObject(DatastoreRepositoryFactory.this.applicationContext);
        evaluationContext.addPropertyAccessor(new BeanFactoryAccessor());
        evaluationContext.setBeanResolver(
            new BeanFactoryResolver(DatastoreRepositoryFactory.this.applicationContext));
        return evaluationContext;
      }

      @Override
      public <T extends Parameters<?, ?>> EvaluationContext getEvaluationContext(
          T parameters, Object[] parameterValues, ExpressionDependencies expressionDependencies) {
        StandardEvaluationContext evaluationContext =
            (StandardEvaluationContext)
                evaluationContextProvider.getEvaluationContext(
                    parameters, parameterValues, expressionDependencies);

        evaluationContext.setRootObject(DatastoreRepositoryFactory.this.applicationContext);
        evaluationContext.addPropertyAccessor(new BeanFactoryAccessor());
        evaluationContext.setBeanResolver(
            new BeanFactoryResolver(DatastoreRepositoryFactory.this.applicationContext));
        return evaluationContext;
      }

      @Override
      public EvaluationContextProvider getEvaluationContextProvider() {
        return (EvaluationContextProvider) evaluationContextProvider;
      }
    };
  }
}
