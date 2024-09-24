package com.likelion.web;

// import org.bitcoinj.core.AddressFormatException;
// import org.bitcoinj.core.InsufficientMoneyException;
// import org.bitcoinj.wallet.Wallet.BadWalletEncryptionKeyException;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.serializer.GenericToStringSerializer;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.scheduling.annotation.EnableScheduling;
// import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;
import org.springframework.web.reactive.config.EnableWebFlux;

@SpringBootApplication
@EnableWebFlux
@EnableScheduling
// @EnableR2dbcRepositories
// @Slf4j
public class WebApplication {

    public static void main(String[] args) {
        // NetworkParameters params = TestNet3Params.get();

        // WalletAppKit kit = new WalletAppKit(params, new File("."), "baeldungkit") {
        // @Override
        // protected void onSetupCompleted() {
        // log.info("Wallet created and loaded successfully.");
        // log.info("Receive address: " + wallet().currentReceiveAddress());
        // log.info("Seed Phrase: " + wallet().getKeyChainSeed());
        // log.info("Balance: " + wallet().getBalance()
        // .toFriendlyString());
        // log.info("Public Key: " +
        // wallet().findKeyFromAddress(wallet().currentReceiveAddress())
        // .getPublicKeyAsHex());
        // // log.info("Private Key: " +
        // wallet().findKeyFromAddress(wallet().currentReceiveAddress())
        // // .getPrivateKeyAsHex());
        // log.info("Private Key: " + "123asd");
        // // wallet().encrypt("password");

        // }
        // };

        // kit.startAsync();
        // kit.awaitRunning();
        // kit.setAutoSave(true);

        // kit.wallet()
        // .addCoinsReceivedEventListener((wallet, tx, prevBalance, newBalance) -> {
        // log.info("-----> coins resceived: " + tx.getTxId());
        // log.info("received: " + tx.getValue(wallet));
        // });
        // kit.wallet()
        // .addCoinsSentEventListener((wallet, tx, prevBalance, newBalance) ->
        // log.info("new balance: " + newBalance.toFriendlyString()));

        SpringApplication.run(WebApplication.class, args);
    }

    @Bean
    CommandLineRunner myCommandLineRunner() {
        return (args) -> {
            // Your code logic here
            System.out.println("Executing CommandLineRunner bean... ok");
        };
    }

    @Bean
    ReactiveRedisTemplate<String, Long> reactiveRedisTemplate(ReactiveRedisConnectionFactory factory) {
        JdkSerializationRedisSerializer jdkSerializationRedisSerializer = new JdkSerializationRedisSerializer();
        StringRedisSerializer stringRedisSerializer = StringRedisSerializer.UTF_8;
        GenericToStringSerializer<Long> longToStringSerializer = new GenericToStringSerializer<>(Long.class);
        ReactiveRedisTemplate<String, Long> template = new ReactiveRedisTemplate<>(factory,
                RedisSerializationContext.<String, Long>newSerializationContext(jdkSerializationRedisSerializer)
                        .key(stringRedisSerializer).value(longToStringSerializer).build());
        return template;
    }

    @Bean
    @Primary
    ReactiveRedisConnectionFactory reactiveRedisConnectionFactory() {
        RedisStandaloneConfiguration config = new RedisStandaloneConfiguration();
        config.setHostName("redis-19136.c334.asia-southeast2-1.gce.redns.redis-cloud.com");
        config.setPort(19136);
        config.setPassword("qvyE3XCt0y0aSOAKJHBSNhrVHNWhBhV4");

        return new LettuceConnectionFactory(config);
    }

    // @Bean
    // ApplicationRunner applicationRunner(JobLauncher jobLauncher, Job job) {
    // return args -> jobLauncher.run(job, new JobParameters());
    // }

}
