INSERT INTO endereco (id, logradouro, numero, complemento, bairro, cidade, estado, cep) VALUES
    (1, 'R. Miguel Ângelo', '96', '', 'Maria da Graça', 'Rio de Janeiro', 'RJ', '20785-220');

INSERT INTO empresa (id, razao_social, nome_fantasia, cnpj, telefone, endereco_id) VALUES
    (1, 'Empresa Projeto B LTDA', 'Projeto B', '11.111.111/0001-11', '(31) 99999-0000', 1);

INSERT INTO filial (id, nome, telefone, email, endereco_id, empresa_id) VALUES
    (1, 'Filial Matriz', '(31) 98888-0000', 'matriz@exemplo.com', 1, 1);

INSERT INTO usuario (id, nome, cpf, email, senha, data_de_nascimento, telefone) VALUES
    (1, 'Usuario Administrador', '123.456.789-00', 'admin@exemplo.com', '$2a$10$up.s34l9K3wNvzi38gM6hONibu/VYsnxNnhgMC/rNHLH9fFocri9a', '2000-01-01', '(21) 91234-5678');

INSERT INTO operador (id, usuario_id, filial_id, cargo) VALUES
    (1, 1, 1, 'Administrador');