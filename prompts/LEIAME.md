# Prompts e esquemas dos agentes

Versão congelada antes das execuções de avaliação: commit `38fe9a1` do repositório da aplicação. Os arquivos foram gerados pelo próprio código da aplicação e correspondem ao que cada agente recebe.

## Prompts montados

Cada prompt reúne o papel do agente, as orientações de entrada, as tarefas que lhe cabem e as restrições comuns. O hash é o registrado na configuração congelada de cada execução.

| Arquivo | Agente | Tarefas | SHA-256 |
|---|---|---|---|
| `agente1.md` | Agente 1 (análise, casos e resposta às devoluções) | analise, casos, devolucao | `1257b21933e756694bb1439071529dbc0f26ec84c964820dc02b2872899c9878` |
| `agente2.md` | Agente 2 (testes unitários e correção) | testes | `f971370bf2c1be9d02cb2fb2a6a44e583163c88f4bdbc8f82e685ebd0f5fef32` |
| `agente3.md` | Agente 3 (revisão dos testes) | revisao | `fa086b4a73af5ee7a21ab06e2e8b3e8dd3c6e7ccfbac807b2f0b0e28eaf13ff9` |
| `agente4.md` | Agente 4 (avaliação dos resultados) | avaliacao | `9d2e4df4305f5ec4f78593c148ca8cb8217f36c4287780585dc68454463eb3b2` |
| `agente_central.md` | Agente central (análise, casos, testes, revisão e resposta às devoluções) | analise, casos, devolucao, testes, revisao | `3cdd7133f80af1d0e2a1e24cf4e714fd77f4c3cee517dcdda7d144c55f186d51` |

## Esquemas JSON de saída

A produção e a correção dos testes compartilham o mesmo esquema.

| Arquivo | Contrato | SHA-256 |
|---|---|---|
| `esquemas/analise.json` | ANALYSIS_SCHEMA | `66e703a9ae3cd2e2602c7c58f618e13982e98c49bef27538323850e2fdd5d9fe` |
| `esquemas/casos.json` | CASES_SCHEMA | `1f2c318a340a4ecc1e88f50b25a2f1caf197969074f30c834287e357934b6be0` |
| `esquemas/testes_e_correcao.json` | TESTS_SCHEMA | `eec70e3279854f148d933985f3efa2ec47d155f0f20adfa9d45dd50d9f004844` |
| `esquemas/revisao.json` | REVIEW_SCHEMA | `3a891a1edb055faeadf4845fa6129ad5a839dff2bffdb2e3aa0137c4295715b4` |
| `esquemas/avaliacao.json` | EVALUATION_SCHEMA | `038028a67f2a445d47d8854730b470096ec4216423d1f66b66f59860aef56bb7` |
| `esquemas/devolucao.json` | RETURN_SCHEMA | `b4af40b6fa21bb3bbe8e553577606ac06af226ab18d2a0a66b9cc3923e33b938` |

## Arquivos de origem das instruções

| Arquivo | SHA-256 |
|---|---|
| `entrada.md` | `4984aa9d5c7ee1d7c16fbf9ffa2f977fc5aff3f62d19f800455ea476c80cc8fb` |
| `papel-agent1.md` | `125399d1cfa38d347ee49982fa01c29c53ebd8a781b4741a7380189707aebf69` |
| `papel-agent2.md` | `5d0a81a6d607e40fd27294356a37cdcd69993a366529e39ee6b16a7dd122744e` |
| `papel-agent3.md` | `c1e4b20f01bb4f92ffb270fe8ab308b044704b7d8c3f98979f6777f0fc9ec756` |
| `papel-agent4.md` | `c1ebedbe86d1178c5ac86de36ec510988b141793005aa29164a3e6ce9cc73ca4` |
| `papel-central.md` | `598ebceb9ff2c1ce0dbad6446d29bb2ff3b8265afa1b7c30fbde05ac61f265c6` |
| `restricoes.md` | `49bfd82872c69794f36650b0148eedfde54f74283cead34302352d76ff522686` |
| `tarefa-analise.md` | `81445b8054297e10223f3d9022cb380b4eb39101053817fa16319a6a54cb9dbe` |
| `tarefa-avaliacao.md` | `283456f7027286584264ebf986cbd652e531fb4f0b85dcd4402997ec4659da85` |
| `tarefa-casos.md` | `37806b0f8300e8e1bdaf41356b241e911369729972d6ed3ba2857a4fbe726aff` |
| `tarefa-devolucao.md` | `f7c76bd6d0c727e5fb43b61c341c3e79a7897a7db15e82bc8e097ec09db4a196` |
| `tarefa-revisao.md` | `8ab65540cd12ea95bc8e41f89f98648a69d53125548095f6741a1cd2ae6c00b3` |
| `tarefa-testes.md` | `6f4686100b2bfee7681a9529378933dcf7ac16b04a17ab0d251cd0fd022c814e` |
