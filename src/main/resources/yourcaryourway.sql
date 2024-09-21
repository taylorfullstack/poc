-- Note: Ensure the following SQL databases are created before running this script:
--CREATE DATABASE UserSQLDatabase;
--CREATE DATABASE LiveChatSQLDatabase;
--CREATE DATABASE RentalsSQLDatabase;
--CREATE DATABASE MessagesSQLDatabase;

CREATE TABLE UserSQLDatabase.User (
                                      id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                      username VARCHAR(255) NOT NULL,
                                      email VARCHAR(255) UNIQUE NOT NULL,
                                      first_name VARCHAR(255),
                                      last_name VARCHAR(255),
                                      password VARCHAR(255),
                                      dob DATE,
                                      address VARCHAR(255),
                                      stripe_payment_token VARCHAR(255),
                                      created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                      updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE LiveChatSQLDatabase.ChatMessage (
                                                 id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                                 sender_id BIGINT,
                                                 recipient_id BIGINT,
                                                 content TEXT,
                                                 timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE RentalsSQLDatabase.CarRentalAgency (
                                                    agency_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                                    agency_name VARCHAR(255) NOT NULL,
                                                    city VARCHAR(255),
                                                    address VARCHAR(255),
                                                    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                                    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE RentalsSQLDatabase.CarRentalOffer (
                                                   offer_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                                   agency_id BIGINT,
                                                   city_departure VARCHAR(255),
                                                   city_return VARCHAR(255),
                                                   departure_datetime TIMESTAMP,
                                                   return_datetime TIMESTAMP,
                                                   vehicle_category VARCHAR(255),
                                                   price DECIMAL(10, 2),
                                                   created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                                   updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                                                   FOREIGN KEY (agency_id) REFERENCES RentalsSQLDatabase.CarRentalAgency(agency_id)
);

CREATE TABLE RentalsSQLDatabase.Reservation (
                                                reservation_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                                user_id BIGINT,
                                                offer_id BIGINT,
                                                reservation_status VARCHAR(50),
                                                reserved_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                                payment_status VARCHAR(50),
                                                payment_method VARCHAR(50),
                                                created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                                updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                                                FOREIGN KEY (offer_id) REFERENCES RentalsSQLDatabase.CarRentalOffer(offer_id)
);


CREATE TABLE MessagesSQLDatabase.SupportTicket (
                                                   ticket_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                                   user_id BIGINT,
                                                   subject VARCHAR(255),
                                                   status VARCHAR(50),
                                                   created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                                   updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                                                   closed_at TIMESTAMP
);

CREATE TABLE MessagesSQLDatabase.Message (
                                             message_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                             ticket_id BIGINT,
                                             user_id BIGINT,
                                             message_content TEXT,
                                             created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                             updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                                             FOREIGN KEY (ticket_id) REFERENCES MessagesSQLDatabase.SupportTicket(ticket_id)
);


