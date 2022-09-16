# redisson-boot-starter

## Using common spring boot settings:
   All other configurations except "redisson" are used spring boot redis config

``` yml
spring:
  redis:
    database: 
    host:
    port:
    password:
    ssl: 
    timeout:
    cluster:
      nodes:
    sentinel:
      master:
      nodes:
    redisson:
      enabled: true    
```


## Getting started

  # Latest Version: [![Maven Central](https://img.shields.io/maven-central/v/com.fengzijk/redisson-boot-starter.svg)](https://search.maven.org/search?q=g:com.fengzijka:redisson-boot-starter*)

``` xml
        <dependency>
            <groupId>com.fengzijk</groupId>
            <artifactId>redisson-boot-starter</artifactId>
            <version>Latest Version</version>
        </dependency>
```
## example
``` java
       // 直接使用key加锁 用key解锁
        RedissonLockUtil.lock("111111");
        RedissonLockUtil.unlock("111111");
        
       
       //获取等待锁加锁是否成功
        Boolean aBoolean = RedissonLockUtil.tryLock(RedisKeyBuilder.keyBuilder("1111", ""), TimeUnit.SECONDS, 1, 2);
        //获取等待锁对象
        RLock lock = RedissonLockUtil.lock(RedisKeyBuilder.keyBuilder("1111", ""), TimeUnit.SECONDS, 1, 2);
 
        // 使用锁对象解锁               
        RedissonLockUtil.unlock(lock);
```
