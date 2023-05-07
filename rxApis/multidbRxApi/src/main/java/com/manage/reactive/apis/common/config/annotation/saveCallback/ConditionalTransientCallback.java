// package com.manage.reactive.apis.common.config.annotation.saveCallback;


// import java.lang.reflect.Field;
// import java.util.HashMap;
// import java.util.HashMap;

// import java.util.Map;

// import org.reactivestreams.Publisher;
// import org.springframework.beans.factory.annotation.Value;
// import org.springframework.boot.context.properties.ConfigurationProperties;
// import org.springframework.data.r2dbc.mapping.event.BeforeConvertCallback;
// import org.springframework.data.relational.core.sql.SqlIdentifier;
// import org.springframework.stereotype.Component;

// import com.fasterxml.jackson.databind.ObjectMapper;
// import com.manage.reactive.apis.common.config.annotation.ConditionalTransient;

// import reactor.core.publisher.Mono;


// @Component
// @ConfigurationProperties
// public class ConditionalTransientCallback implements BeforeConvertCallback<Object> {

//     @Value("${common.db}")
//     private String dbType;

//     @Override
//     public Publisher<Object> onBeforeConvert(Object entity, SqlIdentifier table) {

//         Object finalEntity = new Object();
//         ObjectMapper objectMapper = new ObjectMapper();
//         Map conversionMap = new HashMap(); //MyBatis처럼 형이 정해져 있지 않은것처럼 일단 생성
        

//         if (dbType.equals("rdb")) {

//             Class<?> entityClass = entity.getClass(); //Entity의 .class를 가져온다.
//             Field[] fields = entityClass.getDeclaredFields(); //entity의 속성들을 가져온다.

//             //field를 돌면서 Annotation을 확인하도록 한다.
//             for (Field targetField : fields) {
//                 //field.isAnnotationPresent로 확인 가능 여기서 내가 만들어 놓은 ConditionalTransient annotation을 확인한다.
//                 if (targetField.isAnnotationPresent(ConditionalTransient.class)) {
//                         //ConditionalTransient annotation = field.getAnnotation(ConditionalTransient.class);
//                         //String dbType = annotation.dbType();
//                         targetField.setAccessible(true);
//                         try {
//                             targetField.set(entity, null);
//                         } catch (IllegalAccessException e) {
//                             throw new RuntimeException(e);
//                         }
//                     }else{
//                         //entity Object를 새로 만들어주자 여기서
//                         targetField.setAccessible(true); 
//                         try {
//                             conversionMap.put(targetField.getName(), targetField.get(entity));
//                         } catch (IllegalArgumentException e) {
//                             e.printStackTrace();
//                         } catch (IllegalAccessException e) {
//                             e.printStackTrace();
//                         }
//                     }
//                 }

//             finalEntity = objectMapper.convertValue(conversionMap, Object.class);
//             System.out.println(finalEntity);

//         }

            
            
//         return Mono.just(finalEntity);
       
//     }


// }