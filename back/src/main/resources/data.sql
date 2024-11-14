INSERT INTO `roles` VALUES (1,'ADMIN'),(2,'USER');
INSERT INTO `users` VALUES (1,'2024-11-06 20:15:28.000000','2024-11-06 20:15:33.000000','admin@test.com','admin','$2a$10$kKCokWsiAbvhGPbG6KJbHe5VFbK27bnfJaboHZXywevm5RpY4aFqS');
INSERT INTO `users` VALUES (2,'2024-11-06 20:15:28.000000','2024-11-06 20:15:33.000000','user@test.com','user','$2a$10$kKCokWsiAbvhGPbG6KJbHe5VFbK27bnfJaboHZXywevm5RpY4aFqS');
INSERT INTO `users_roles` VALUES (1, 1);
INSERT INTO `users_roles` VALUES (2, 2);