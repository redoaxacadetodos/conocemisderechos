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
			username = "postgres"
			password = "t3mp0r4l"
			url = "jdbc:postgresql://172.31.0.101:5432/cednna"
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
      username = "cednna"
      password = "c3dnn44cc3ss"
	    //url = "jdbc:postgresql://localhost:5432/cednna"
      //url = "jdbc:postgresql://172.31.0.101:5432/cednna_curso"
      url = "jdbc:postgresql://172.30.0.14:5432/prod_cednna"
      dbCreate = "validate"
      }       
    }
}
