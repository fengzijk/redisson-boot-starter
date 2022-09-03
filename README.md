# redisson-boot-starter

## Using common spring boot settings:

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
        RedissonLockUtil.lock("111111");
        
        RedissonLockUtil.unlock("111111");
        
        Boolean aBoolean = RedissonLockUtil.tryLock(RedisKeyBuilder.keyBuilder("1111", ""), TimeUnit.SECONDS, 1, 2);

        RLock lock = RedissonLockUtil.lock(RedisKeyBuilder.keyBuilder("1111", ""), TimeUnit.SECONDS, 1, 2);
        
        RedissonLockUtil.unlock(lock);
```
