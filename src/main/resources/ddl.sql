create table cidade (id bigint not null auto_increment, nome varchar(255), estado_id bigint not null, primary key (id)) engine=InnoDB
create table cozinha (id bigint not null auto_increment, nome varchar(30) not null, primary key (id)) engine=InnoDB
create table estado (id bigint not null auto_increment, nome varchar(255), primary key (id)) engine=InnoDB
create table forma_pagamento (id bigint not null auto_increment, descricao varchar(255), primary key (id)) engine=InnoDB
create table grupo (id bigint not null auto_increment, nome varchar(255), primary key (id)) engine=InnoDB
create table grupo_permissao (grupo_id bigint not null, permissao_id bigint not null) engine=InnoDB
create table item_pedido (id bigint not null auto_increment, observacoes varchar(255), preco_total decimal(19,2) not null, preco_unitario decimal(19,2) not null, quantidade integer not null, produto_id bigint not null, primary key (id)) engine=InnoDB
create table pedido (id bigint not null auto_increment, data_cancelamento datetime(6), data_confirmacao datetime(6), data_criacao datetime(6) not null, data_entrega datetime(6), endereco_bairro varchar(255) not null, endereco_cep varchar(255) not null, endereco_complemento varchar(255), endereco_logradouro varchar(255) not null, endereco_numero varchar(255) not null, status_pedido integer, subtotal decimal(19,2) not null, valor_total decimal(19,2) not null, endereco_cidade_id bigint, forma_pagamento_id bigint not null, item_pedido_id bigint not null, restaurante_id bigint not null, cliente_id bigint not null, primary key (id)) engine=InnoDB
create table permissao (id bigint not null auto_increment, descricao varchar(255), nome varchar(255), primary key (id)) engine=InnoDB
create table produto (id bigint not null auto_increment, ativo bit not null, descricao varchar(255), nome varchar(255), preco decimal(19,2), restaurante_id bigint not null, primary key (id)) engine=InnoDB
create table restaurante (id bigint not null auto_increment, data_atualizacao dateTime not null, data_cadastro dateTime not null, endereco_bairro varchar(255) not null, endereco_cep varchar(255) not null, endereco_complemento varchar(255), endereco_logradouro varchar(255) not null, endereco_numero varchar(255) not null, nome varchar(30) not null, taxa_frete decimal(19,2) not null, cozinha_id bigint not null, endereco_cidade_id bigint, primary key (id)) engine=InnoDB
create table restaurante_forma_pagamento (restaurante_id bigint not null, forma_pagamento_id bigint not null) engine=InnoDB
create table usuario (id bigint not null auto_increment, data_cadastro datetime(6), email varchar(255), nome varchar(255), senha varchar(255), primary key (id)) engine=InnoDB
create table usuario_grupo (usuario_id bigint not null, grupo_id bigint not null) engine=InnoDB
alter table item_pedido add constraint UK_r5pmilksmhlmh5mccbvplg8b7 unique (produto_id)
alter table cidade add constraint FKkworrwk40xj58kevvh3evi500 foreign key (estado_id) references estado (id)
alter table grupo_permissao add constraint FKh21kiw0y0hxg6birmdf2ef6vy foreign key (permissao_id) references permissao (id)
alter table grupo_permissao add constraint FKta4si8vh3f4jo3bsslvkscc2m foreign key (grupo_id) references grupo (id)
alter table item_pedido add constraint FKtk55mn6d6bvl5h0no5uagi3sf foreign key (produto_id) references produto (id)
alter table pedido add constraint FKk987vfg9cpgx7qxj3166fdqig foreign key (endereco_cidade_id) references cidade (id)
alter table pedido add constraint FKqaa411xsl0xu4tkvt1wpccd3b foreign key (forma_pagamento_id) references forma_pagamento (id)
alter table pedido add constraint FK2pdbaim24e0dhnwr7dpwagxbh foreign key (item_pedido_id) references item_pedido (id)
alter table pedido add constraint FK3eud5cqmgsnltyk704hu3qj71 foreign key (restaurante_id) references restaurante (id)
alter table pedido add constraint FK37ms39e5dvx6m05hftvx9uavk foreign key (cliente_id) references usuario (id)
alter table produto add constraint FKb9jhjyghjcn25guim7q4pt8qx foreign key (restaurante_id) references restaurante (id)
alter table restaurante add constraint FK76grk4roudh659skcgbnanthi foreign key (cozinha_id) references cozinha (id)
alter table restaurante add constraint FKbc0tm7hnvc96d8e7e2ulb05yw foreign key (endereco_cidade_id) references cidade (id)
alter table restaurante_forma_pagamento add constraint FK7aln770m80358y4olr03hyhh2 foreign key (forma_pagamento_id) references forma_pagamento (id)
alter table restaurante_forma_pagamento add constraint FKa30vowfejemkw7whjvr8pryvj foreign key (restaurante_id) references restaurante (id)
alter table usuario_grupo add constraint FKk30suuy31cq5u36m9am4om9ju foreign key (grupo_id) references grupo (id)
alter table usuario_grupo add constraint FKdofo9es0esuiahyw2q467crxw foreign key (usuario_id) references usuario (id)
insert into cozinha (nome) value ('Tailandesa')
insert into cozinha (nome) value ('Chinesa')
insert into permissao (id, nome, descricao) values (1, 'Admin', 'Acesso de Admin')
insert into permissao (id, nome, descricao) values (2, 'User', 'Acesso de user comum')
insert into grupo (id, nome) values (1, 'Grupo de usuarios admin')
insert into restaurante (id, nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao) values (1, 'Thai Gourmet', 10, 1, utc_timestamp, utc_timestamp)
insert into restaurante (id, nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao) values (2, 'Thai Delivery', 20, 1, utc_timestamp, utc_timestamp)
insert into restaurante (id, nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao) values (3, 'China In Box', 20, 2, utc_timestamp, utc_timestamp)
insert into produto (id, nome, descricao, preco, ativo, restaurante_id) values (1, 'Sushi', 'Arroz e algas', 10, true, 1)
insert into produto (id, nome, descricao, preco, ativo, restaurante_id) values (2, 'Sushi', 'Arroz', 20, true, 1)
insert into estado (nome) values ('São Paulo')
insert into estado (nome) values ('Rio de Janeiro')
insert into cidade (nome, estado_id) values ('São Paulo', 1)
insert into cidade (nome, estado_id) values ('Rio de Janeiro', 2)
insert into forma_pagamento (id, descricao) values (1, 'Crédito')
insert into forma_pagamento (id, descricao) values (2, 'Dinheiro')
insert into forma_pagamento (id, descricao) values (3, 'Débito')
insert into restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) values (1, 1), (1, 2), (2, 1)
insert into grupo_permissao (grupo_id, permissao_id) values (1, 1), (1, 2)
create table cidade (id bigint not null auto_increment, nome varchar(255), estado_id bigint not null, primary key (id)) engine=InnoDB
create table cozinha (id bigint not null auto_increment, nome varchar(30) not null, primary key (id)) engine=InnoDB
create table estado (id bigint not null auto_increment, nome varchar(255), primary key (id)) engine=InnoDB
create table forma_pagamento (id bigint not null auto_increment, descricao varchar(255), primary key (id)) engine=InnoDB
create table grupo (id bigint not null auto_increment, nome varchar(255), primary key (id)) engine=InnoDB
create table grupo_permissao (grupo_id bigint not null, permissao_id bigint not null) engine=InnoDB
create table item_pedido (id bigint not null auto_increment, observacoes varchar(255), preco_total decimal(19,2) not null, preco_unitario decimal(19,2) not null, quantidade integer not null, produto_id bigint not null, primary key (id)) engine=InnoDB
create table pedido (id bigint not null auto_increment, data_cancelamento datetime(6), data_confirmacao datetime(6), data_criacao datetime(6) not null, data_entrega datetime(6), endereco_bairro varchar(255) not null, endereco_cep varchar(255) not null, endereco_complemento varchar(255), endereco_logradouro varchar(255) not null, endereco_numero varchar(255) not null, status_pedido integer, subtotal decimal(19,2) not null, valor_total decimal(19,2) not null, endereco_cidade_id bigint, forma_pagamento_id bigint not null, item_pedido_id bigint not null, restaurante_id bigint not null, cliente_id bigint not null, primary key (id)) engine=InnoDB
create table permissao (id bigint not null auto_increment, descricao varchar(255), nome varchar(255), primary key (id)) engine=InnoDB
create table produto (id bigint not null auto_increment, ativo bit not null, descricao varchar(255), nome varchar(255), preco decimal(19,2), restaurante_id bigint not null, primary key (id)) engine=InnoDB
create table restaurante (id bigint not null auto_increment, data_atualizacao dateTime not null, data_cadastro dateTime not null, endereco_bairro varchar(255) not null, endereco_cep varchar(255) not null, endereco_complemento varchar(255), endereco_logradouro varchar(255) not null, endereco_numero varchar(255) not null, nome varchar(30) not null, taxa_frete decimal(19,2) not null, cozinha_id bigint not null, endereco_cidade_id bigint, primary key (id)) engine=InnoDB
create table restaurante_forma_pagamento (restaurante_id bigint not null, forma_pagamento_id bigint not null) engine=InnoDB
create table usuario (id bigint not null auto_increment, data_cadastro datetime(6), email varchar(255), nome varchar(255), senha varchar(255), primary key (id)) engine=InnoDB
create table usuario_grupo (usuario_id bigint not null, grupo_id bigint not null) engine=InnoDB
alter table item_pedido add constraint UK_r5pmilksmhlmh5mccbvplg8b7 unique (produto_id)
alter table cidade add constraint FKkworrwk40xj58kevvh3evi500 foreign key (estado_id) references estado (id)
alter table grupo_permissao add constraint FKh21kiw0y0hxg6birmdf2ef6vy foreign key (permissao_id) references permissao (id)
alter table grupo_permissao add constraint FKta4si8vh3f4jo3bsslvkscc2m foreign key (grupo_id) references grupo (id)
alter table item_pedido add constraint FKtk55mn6d6bvl5h0no5uagi3sf foreign key (produto_id) references produto (id)
alter table pedido add constraint FKk987vfg9cpgx7qxj3166fdqig foreign key (endereco_cidade_id) references cidade (id)
alter table pedido add constraint FKqaa411xsl0xu4tkvt1wpccd3b foreign key (forma_pagamento_id) references forma_pagamento (id)
alter table pedido add constraint FK2pdbaim24e0dhnwr7dpwagxbh foreign key (item_pedido_id) references item_pedido (id)
alter table pedido add constraint FK3eud5cqmgsnltyk704hu3qj71 foreign key (restaurante_id) references restaurante (id)
alter table pedido add constraint FK37ms39e5dvx6m05hftvx9uavk foreign key (cliente_id) references usuario (id)
alter table produto add constraint FKb9jhjyghjcn25guim7q4pt8qx foreign key (restaurante_id) references restaurante (id)
alter table restaurante add constraint FK76grk4roudh659skcgbnanthi foreign key (cozinha_id) references cozinha (id)
alter table restaurante add constraint FKbc0tm7hnvc96d8e7e2ulb05yw foreign key (endereco_cidade_id) references cidade (id)
alter table restaurante_forma_pagamento add constraint FK7aln770m80358y4olr03hyhh2 foreign key (forma_pagamento_id) references forma_pagamento (id)
alter table restaurante_forma_pagamento add constraint FKa30vowfejemkw7whjvr8pryvj foreign key (restaurante_id) references restaurante (id)
alter table usuario_grupo add constraint FKk30suuy31cq5u36m9am4om9ju foreign key (grupo_id) references grupo (id)
alter table usuario_grupo add constraint FKdofo9es0esuiahyw2q467crxw foreign key (usuario_id) references usuario (id)
insert into cozinha (nome) value ('Tailandesa')
insert into cozinha (nome) value ('Chinesa')
insert into permissao (id, nome, descricao) values (1, 'Admin', 'Acesso de Admin')
insert into permissao (id, nome, descricao) values (2, 'User', 'Acesso de user comum')
insert into grupo (id, nome) values (1, 'Grupo de usuarios admin')
insert into restaurante (id, nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao) values (1, 'Thai Gourmet', 10, 1, utc_timestamp, utc_timestamp)
insert into restaurante (id, nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao) values (2, 'Thai Delivery', 20, 1, utc_timestamp, utc_timestamp)
insert into restaurante (id, nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao) values (3, 'China In Box', 20, 2, utc_timestamp, utc_timestamp)
insert into produto (id, nome, descricao, preco, ativo, restaurante_id) values (1, 'Sushi', 'Arroz e algas', 10, true, 1)
insert into produto (id, nome, descricao, preco, ativo, restaurante_id) values (2, 'Sushi', 'Arroz', 20, true, 1)
insert into estado (nome) values ('São Paulo')
insert into estado (nome) values ('Rio de Janeiro')
insert into cidade (nome, estado_id) values ('São Paulo', 1)
insert into cidade (nome, estado_id) values ('Rio de Janeiro', 2)
insert into forma_pagamento (id, descricao) values (1, 'Crédito')
insert into forma_pagamento (id, descricao) values (2, 'Dinheiro')
insert into forma_pagamento (id, descricao) values (3, 'Débito')
insert into restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) values (1, 1), (1, 2), (2, 1)
insert into grupo_permissao (grupo_id, permissao_id) values (1, 1), (1, 2)
