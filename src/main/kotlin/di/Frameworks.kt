package com.where.to.go.di

import com.where.to.go.domain.serivservice.GreetingService
import com.where.to.go.domain.serivservice.HelloService
import io.ktor.server.application.*
import io.ktor.server.plugins.di.*
import org.koin.dsl.module
import org.koin.ktor.plugin.Koin
import org.koin.logger.slf4jLogger

fun Application.configureFrameworks() {
    install(Koin) {
        slf4jLogger()
        modules(module {
            single<HelloService> {
                HelloService {
                    println(environment.log.info("Hello, World!"))
                }
            }
        })
    }
    dependencies {
        provide { GreetingService { "Hello, World!" } }
    }
}
