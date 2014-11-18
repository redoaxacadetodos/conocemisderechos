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
			username = "USERNAME"
			password = "PASSWORD"
			url = "jdbc:postgresql://HOST:5432/DB"
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
      pooled = false
      driverClassName = "org.postgresql.Driver"
      username = "USERNAME"
      password = "PASSWORD"	   
      url = "jdbc:postgresql://HOST:5432/DB"
      dbCreate = "update"
      }       
    }
}
