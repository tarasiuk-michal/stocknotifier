# Stock Price Notifier System 📈🔔

A real-time stock price alert system that notifies users when subscribed stocks exceed price change thresholds.

## Features ✨
- **Stock Subscription Management**: REST API for users to subscribe/unsubscribe to stocks
- **Real-time Price Monitoring**: Kafka-powered price updates with configurable thresholds
- **Multi-channel Notifications**: Base implementation for alerts (extendable to email/SMS/WebSocket)
- **Auto-generated API Docs**: OpenAPI 3.0 documentation
- **Azure SQL Integration**: Cloud-ready database configuration
- **Scalable Architecture**: Decoupled services using Kafka messaging

## Technologies 🛠️
- **Backend**: Spring Boot 3.2
- **Messaging**: Apache Kafka
- **Database**: Azure SQL (Microsoft SQL Server)
- **ORM**: Hibernate 6
- **API Docs**: SpringDoc OpenAPI
- **Connection Pool**: HikariCP
- **Build Tool**: Maven
