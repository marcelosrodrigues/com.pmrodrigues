    create table Bairro (
        id bigint not null auto_increment,
        nome varchar(255) not null,
        cidade_id bigint not null,
        primary key (id)
    );

    create table Cidade (
        id bigint not null auto_increment,
        nome varchar(255) not null,
        estado_id bigint not null,
        primary key (id)
    );

    create table Endereco (
        id bigint not null auto_increment,
        complemento varchar(255),
        numero varchar(255),
        logradouro_id bigint not null,
        primary key (id)
    );

    create table Estado (
        id bigint not null,
        nome varchar(255) not null,
        uf char(2) not null,
        primary key (id)
    );

    create table Logradouro (
        id bigint not null auto_increment,
        cep varchar(255) not null,
        logradouro varchar(255) not null,
        bairro_id bigint not null,
        primary key (id)
    );

    create table PessoaFisica (
        id bigint not null auto_increment,
        email varchar(255),
        nome varchar(255),
        cpf varchar(15) not null,
        dataNascimento date,
        endereco_id bigint not null,
        pai_id bigint,
        primary key (id)
    );

    create table PessoaJuridica (
        id bigint not null auto_increment,
        email varchar(255),
        nome varchar(255),
        cnpj varchar(17) not null,
        endereco_id bigint not null,
        primary key (id)
    );

    alter table Estado
        add constraint UK_UF unique (uf);

    alter table PessoaFisica
        add constraint UK_CPF unique (cpf);

    alter table Bairro
        add index FK_CIDADE_BAIRRO (cidade_id),
        add constraint FK_CIDADE_BAIRRO
        foreign key (cidade_id)
        references Cidade (id);

    alter table Cidade
        add index FK_CIDADE_ESTADO (estado_id),
        add constraint FK_CIDADE_ESTADO
        foreign key (estado_id)
        references Estado (id);

    alter table Endereco
        add index FK_ENDERECO_LOGRADOURO (logradouro_id),
        add constraint FK_ENDERECO_LOGRADOURO
        foreign key (logradouro_id)
        references Logradouro (id);

    alter table Logradouro
        add index FK_BAIRRO_LOGRADOURO (bairro_id),
        add constraint FK_BAIRRO_LOGRADOURO
        foreign key (bairro_id)
        references Bairro (id);

    alter table PessoaFisica
        add index FK_PESSOA_FISICA_ENDERECO (endereco_id),
        add constraint FK_PESSOA_FISICA_ENDERECO
        foreign key (endereco_id)
        references Endereco (id);

    alter table PessoaFisica
        add index FK_PAI_PESSOA_FISICA (pai_id),
        add constraint FK_PAI_PESSOA_FISICA
        foreign key (pai_id)
        references PessoaFisica (id);

    alter table PessoaJuridica
        add index FK_PESSOA_JURIDICA_ENDERECO (endereco_id),
        add constraint FK_PESSOA_JURIDICA_ENDERECO
        foreign key (endereco_id)
        references Endereco (id);