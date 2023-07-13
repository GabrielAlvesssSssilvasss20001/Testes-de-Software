CREATE TABLE Livro (
	id SERIAL PRIMARY KEY,
	titulo varchar (255),
	descricao varchar (255) NOT NULL,
	disponivel boolean,
	responsavel varchar (255),
	categoria integer NOT NULL,
	data_retirada date
)