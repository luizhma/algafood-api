insert ignore into cozinha (nome) value ('Tailandesa');
insert ignore into cozinha (nome) value ('Chinesa');
insert ignore into permissao (id, nome, descricao) values (1, 'Admin', 'Acesso de Admin');
insert ignore into permissao (id, nome, descricao) values (2, 'User', 'Acesso de user comum');
insert ignore into grupo (id, nome) values (1, 'Grupo de usuarios admin');
insert ignore into restaurante (id, nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao) values (1, 'Thai Gourmet', 10, 1, utc_timestamp, utc_timestamp);
insert ignore into restaurante (id, nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao) values (2, 'Thai Delivery', 20, 1, utc_timestamp, utc_timestamp);
insert ignore into restaurante (id, nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao) values (3, 'China In Box', 20, 2, utc_timestamp, utc_timestamp);
insert ignore into produto (id, nome, descricao, preco, ativo, restaurante_id) values (1, 'Sushi', 'Arroz e algas', 10, true, 1);
insert ignore into produto (id, nome, descricao, preco, ativo, restaurante_id) values (2, 'Sushi', 'Arroz', 20, true, 1);
insert ignore into estado (nome) values ('São Paulo');
insert ignore into estado (nome) values ('Rio de Janeiro');
insert ignore into cidade (nome, estado_id) values ('São Paulo', 1);
insert ignore into cidade (nome, estado_id) values ('Rio de Janeiro', 2);
insert ignore into forma_pagamento (id, descricao) values (1, 'Crédito');
insert ignore into forma_pagamento (id, descricao) values (2, 'Dinheiro');
insert ignore into forma_pagamento (id, descricao) values (3, 'Débito');
insert ignore into restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) values (1, 1), (1, 2), (2, 1);
insert ignore into grupo_permissao (grupo_id, permissao_id) values (1, 1), (1, 2);