package com.likelion.web;

// import org.bitcoinj.core.AddressFormatException;
// import org.bitcoinj.core.InsufficientMoneyException;
// import org.bitcoinj.wallet.Wallet.BadWalletEncryptionKeyException;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
// import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;
import org.springframework.web.reactive.config.EnableWebFlux;

import reactor.netty.http.server.HttpServer;

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
        HttpServer.create()
            .accessLog(true) // Enable access logging
            .bindNow();

        SpringApplication.run(WebApplication.class, args);
    }

    @Bean
    CommandLineRunner myCommandLineRunner() {
        return (args) -> {
            // Your code logic here
            System.out.println("Executing CommandLineRunner bean... ok");
        };
    }

    // @Bean
    // ApplicationRunner applicationRunner(JobLauncher jobLauncher, Job job) {
    //     return args -> jobLauncher.run(job, new JobParameters());
    // }

}
