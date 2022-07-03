/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kredditme.dao;

import com.kredditme.abstractentities.CustomPredicate;
import com.kredditme.abstractentities.OrderBy;
import com.kredditme.abstractentities.PredicateBuilder;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;
import org.hibernate.Session;
import org.springframework.dao.DataAccessException;


public interface IGenericDao {
    

    <T> List<T> loadAllObjectsUsingRestrictions(Class<T> clazz, final List<CustomPredicate> customPredicate, String order);

 
    <T> int countObjectsUsingPredicateBuilder(PredicateBuilder predicateBuilder, Class<T> clazz);


    <T> List<T> loadObjectsUsingPredicateBuilder(PredicateBuilder predicateBuilder, Class<T> clazz);

    <T> List<T> loadObjectsUsingPredicateBuilder(PredicateBuilder predicateBuilder, Class<T> clazz, List<OrderBy> orderBy);

    <T> T loadSingleObjectUsingPredicateBuilder(PredicateBuilder predicateBuilder, Class<T> clazz);

    <T, X extends Number> X sumFieldUsingPredicateBuilder(Class<T> rootClass, PredicateBuilder predicateBuilder, Class<X> sumClass, String sumField,
                                                          List<String> groupByFields);

    <T> List<T> loadAllObjectsWithoutRestrictions(Class<T> clazz, String order);

    <T> List<T> loadAllObjectsWithSingleCondition(Class<T> clazz, CustomPredicate customPredicate, String order);


    <T> T loadObjectWithSingleConditionAllowNull(Class<T> pObjectClass, CustomPredicate customPredicate) throws InstantiationException, IllegalAccessException;

    <T> T  loadObjectById(Class<T> clazz, Long pId) throws DataAccessException, IllegalAccessException, InstantiationException;

    <T> T loadObjectUsingRestriction(Class<T> clazz, List<CustomPredicate> predicates) throws IllegalAccessException, InstantiationException;

    <T> T loadObjectUsingRestrictionAllowNull(Class<T> pObjectClass, List<CustomPredicate> predicates) throws IllegalAccessException, InstantiationException;

    <T> List<T> loadPaginatedObjects(Class<T> clazz, List<CustomPredicate> predicates, int pStartRowNum, int pEndRowNum, String pSortOrder, String pSortCriterion );


    Long getTotalPaginatedObjects(Class<?> clazz,List<CustomPredicate> predicates);

    void  storeObject(Object pObject);

    <T> T loadObjectWithSingleCondition(Class<T> pObjectClass, CustomPredicate customPredicate) throws InstantiationException, IllegalAccessException;


    <T> List<T> loadControlEntity(Class<T> clazz);

    Long saveObject(Object object);

  
    void storeObjectBatch(List<?> pFSaveList);

 
    void storeVectorObjectBatch(Vector<?> pFSaveList);
    <T> boolean isObjectExisting(Class<T> clazz, PredicateBuilder predicateBuilder);
    void deleteObject(Object object);

    Session getCurrentSession();
    Long loadMaxValueByClassAndLongColName(Class<?> clazz, String pLongColumnOrmName);

    Long loadMaxValueByClassClientIdAndColumn(Class<?> clazz, String pLongColumnOrmName, Long pBizClientId, String pBizClientOrmName);

    <T> HashMap<Long,T> loadObjectsAsMap(Class<T> pObjectClass, List<CustomPredicate> predicates, String pMethodName)throws NoSuchMethodException, IllegalAccessException, InvocationTargetException;

    <T> HashMap<?,T> loadObjectsAsMapWithStringKey(Class<T> pObjectClass, List<CustomPredicate> predicates, String pMethodName)throws NoSuchMethodException, IllegalAccessException, InvocationTargetException;

    int getTotalNoOfModelObjectByClass(Class<?> pObjectClass,String pOrmCol ,boolean pDistinct);

   <T> void deleteObjectsWithConditions(Class<T> stepIncreaseBeanClass, List<CustomPredicate> predicates);

    <T> T loadDefaultEntity(Class<T> clazz, List<CustomPredicate> customPredicateList) throws IllegalAccessException, InstantiationException;

    <T> int loadMaxValueByClassAndIntColName(Class<T> pClass, String colName);

 

    Long getTotalPaginatedObjectsByPredicate(Class<?> clazz, List<CustomPredicate> customPredicateList);

    <T> List<T> loadPaginatedObjectsByPredicates(Class<T> pObjectClass, PredicateBuilder predicateBuilder, int pStartRowNum, int pEndRowNum, String pSortOrder, String pSortCriterion);

    
}
