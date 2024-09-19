CREATE TABLE public.users (
                       id INT PRIMARY KEY AUTO_INCREMENT,
                       username VARCHAR(255) NOT NULL,
                       email VARCHAR(255) NOT NULL UNIQUE,
                       password VARCHAR(255) NOT NULL,
                       online BOOLEAN NOT NULL
);

CREATE TABLE IF NOT EXISTS public.chat_message (
                                            id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                            sender_id VARCHAR(255),
                                            recipient_id VARCHAR(255),
                                            content TEXT,
                                            timestamp TIMESTAMP
);