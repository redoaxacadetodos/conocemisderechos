hibernate {
    cache.use_second_level_cache = true
    cache.use_query_cache = false
    cache.region.factory_class = 'net.sf.ehcache.hibernate.EhCacheRegionFactory'
}
// environment specific settings
environments {
    development {
        dataSource {
          loggingSql = true
			pooled = false
			driverClassName = "org.postgresql.Driver"
			username = ""
			password = ""
			url = "jdbc:postgresql://ip:5432/dev_cednna"
            dbCreate = "update"
        }
    }
    test {
        dataSource {wew
            dbCreate = "update"
            url = "jdbc:h2:mem:testDb;MVCC=TRUE;LOCK_TIMEOUT=10000"
        }
    }
    production {
      dataSource {
      loggingSql = false
      pooled = true
      driverClassName = "org.postgresql.Driver"
      username = ""
      password = ""
      url = "jdbc:postgresql://ip:5432/prod_cednna"
      dbCreate = "update"
      properties {
                maxActive = 20
                maxIdle = 10
                minIdle = 2
                initialSize = 10
                minEvictableIdleTimeMillis = 60000
                timeBetweenEvictionRunsMillis = 60000
                maxWait = 10000
            }
      }       
    }
}
