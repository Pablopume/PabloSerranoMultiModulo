module server{
    requires jakarta.jakartaee.web.api;
    requires lombok;
    requires domain;
    requires java.sql;
    requires com.zaxxer.hikari;
    requires com.sun.jna;
    requires de.mkammerer.argon2;
    requires org.apache.logging.log4j;

}