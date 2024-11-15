INSERT INTO `roles` VALUES (1,'ADMIN'),(2,'USER');
INSERT INTO `users` VALUES (1,'2024-11-06 20:15:28.000000','2024-11-06 20:15:33.000000','admin@test.com','admin','$2a$10$kKCokWsiAbvhGPbG6KJbHe5VFbK27bnfJaboHZXywevm5RpY4aFqS');
INSERT INTO `users` VALUES (2,'2024-11-06 20:15:28.000000','2024-11-06 20:15:33.000000','user@test.com','user','$2a$10$kKCokWsiAbvhGPbG6KJbHe5VFbK27bnfJaboHZXywevm5RpY4aFqS');
INSERT INTO `users_roles` VALUES (1, 1);
INSERT INTO `users_roles` VALUES (2, 2);

INSERT INTO `rentals` VALUES (
                              1,
                              1000,
                              326,
                              '2024-11-06 20:15:28.000000',
                              1,
                              '2024-11-06 20:15:28.000000',
                              'Une super longue description pour l\'appartement numero 1 de ce site internet de location d\'appartements',
                              'Appartement près de l\'école',
                              'http://localhost:9090/api/files/file/1.jpg'
                             );
INSERT INTO `rentals` VALUES (
                                 2,
                                 1568,
                                 156,
                                 '2024-11-06 20:15:28.000000',
                                 2,
                                 '2024-11-06 20:15:28.000000',
                                 'Une autre et encore mieux que la premiere description sauf que cette fois celle-ci c\'est une super longue description pour l\'appartement numero 2 de ce site internet de location d\'appartements',
                                 'Appartement au 8e étage sans ascenseur',
                                 'http://localhost:9090/api/files/file/2.jpg'
                             );
INSERT INTO `rentals` VALUES (
                                 3,
                                 132,
                                 3654,
                                 '2024-11-06 20:15:28.000000',
                                 1,
                                 '2024-11-06 20:15:28.000000',
                                 'Une petit description pour l\'appartement numero 3',
                                 'Charmant appartement trop petit',
                                 'http://localhost:9090/api/files/file/3.jpg'
                             );