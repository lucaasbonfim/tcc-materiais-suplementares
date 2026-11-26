# Código-fonte dos projetos avaliados (cópias anonimizadas)

Cópias do código dos três projetos acadêmicos utilizados na avaliação, publicadas com autorização de todos os
autores. Os projetos são identificados por códigos para preservar o anonimato.

| Arquivo | Domínio | Arquivos | SHA-256 |
|---|---|---|---|
| `projeto-a.zip` | Gestão acadêmica | 158 | `a2c376904057b51930b20a0341176a5acc9f9030540587a0734339b40fb79767` |
| `projeto-b.zip` | Locação de veículos | 122 | `5a6d88eff1440e438c354251f3a436bc98884445c0db1edd84b58a3181028966` |
| `projeto-c.zip` | Venda de passagens | 131 | `7399a4e502a6f2989d633844f90f63ff2073acaae3be52c6ccec60f6bda2ece4` |

## O que cada arquivo contém

Somente a parte do projeto utilizada no experimento: o `pom.xml` e a pasta `src/main` (código Java e recursos).
O histórico de versões, os testes escritos pelos autores, a interface separada e os documentos do repositório
original não foram incluídos.

## O que foi alterado em relação ao original

- O pacote base passou a ser `projetoa`, `projetob` ou `projetoc`, e a classe principal passou a se chamar
  `Application`.
- O nome original do sistema foi trocado pelo código do projeto onde aparecia: `pom.xml`, nome da aplicação,
  títulos de páginas, textos de documentação da API e comentários.
- Endereço, usuário e senha de banco de dados nos arquivos de configuração foram trocados por `REMOVIDO`.
- No Projeto B, o nome, o e-mail e a data de nascimento de um usuário nos dados iniciais do banco foram trocados
  por valores genéricos.

As classes, os métodos e as regras de negócio não foram alterados. As três cópias foram compiladas com Java 21
depois das alterações.

## Como conferir

Calcule o SHA-256 de cada arquivo e compare com a tabela acima. Em um terminal: `sha256sum projeto-a.zip`.

## Ramos utilizados no experimento

O mesmo conteúdo de cada arquivo está em um ramo próprio deste repositório, com o `pom.xml` na raiz. As execuções
de avaliação utilizaram o código desses commits.

| Projeto | Ramo | Commit |
|---|---|---|
| Projeto A | `projeto-a` | `6147a60d1cbfdce590dee6455a04b13dba3cc0a9` |
| Projeto B | `projeto-b` | `f567ea6baa537ab1545cdda2dcddf3d0f4589ae7` |
| Projeto C | `projeto-c` | `1bd2572d9b2159860c8913a10cbbaed78cfa9de2` |
