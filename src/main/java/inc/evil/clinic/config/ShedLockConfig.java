package inc.evil.clinic.config;

import net.javacrumbs.shedlock.core.LockProvider;
import net.javacrumbs.shedlock.provider.jdbctemplate.JdbcTemplateLockProvider;
import net.javacrumbs.shedlock.spring.annotation.EnableSchedulerLock;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Configuration(proxyBeanMethods = false)
@EnableSchedulerLock(defaultLockAtMostFor = "${shedlock.default-lock-at-most-for:10s}")
public class ShedLockConfig {
    @Bean
    public LockProvider lockProvider(DataSource dataSource, @Value("${shedlock.lock-table-name:shedlock}") String lockTableName) {
        return new JdbcTemplateLockProvider(JdbcTemplateLockProvider.Configuration.builder()
                .withJdbcTemplate(new JdbcTemplate(dataSource))
                .withTableName(lockTableName)
                .usingDbTime()
                .build());
    }
}
