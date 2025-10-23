# CineNow APP

CineNow é um aplicativo Android que permite aos usuários explorar filmes populares em tempo real, obtendo informações diretamente da API do The Movie Database (TMDB). Com uma interface amigável, os usuários podem visualizar detalhes de filmes, incluindo sinopse, data de lançamento, classificação, e muito mais.


---
## ✨ Funcionalidades
- Filmes Populares: Visualize uma lista de filmes populares atualizada diariamente.
- Detalhes do Filme: Obtenha informações detalhadas sobre cada filme, como título, sinopse, data de lançamento, classificação, e cartaz.
- Interface Intuitiva: Navegação fácil e design limpo para uma melhor experiência do usuário.
---
## 🧱 Arquitetura e Padrões
O projeto foi desenvolvido seguindo **boas práticas de arquitetura Android**, garantindo um código mais limpo, organizado e fácil de manter.

- **Arquitetura:** MVVM (Model–View–ViewModel)
- **Padrão de Repositório:** *Online First* (consulta a dados locais ou remotos)
- **Camadas bem definidas:**
  - **UI (Compose):** Exibição e interação com o usuário  
  - **ViewModel:** Gerenciamento de estado e lógica de apresentação  
  - **Repository:** Abstração de fontes de dados (API, cache local, etc.)


---
## :camera_flash: Screenshots
<!-- You can add more screenshots here if you like -->
<img src="https://github.com/user-attachments/assets/a90bb133-c063-4f90-abb8-fd7c3d254d13" width=260/> <img src="https://github.com/user-attachments/assets/08b4bfb4-c7b7-4918-a532-8f3acf0a7aa4" width=260/>

---

## 🛠️ Tecnologias
- **Linguagem:** 100% Kotlin  
- **UI:** Jetpack Compose  
  - Column, Row, Modifier, Spacer, LazyRows  
  - Compose Preview, NavHostController, AsyncImage  
- **Networking:** Retrofit + OkHttp3  
- **API:** TMDB API  
- **Gerenciamento de Estado:** ViewModel

---

## License
```
The MIT License (MIT)

Copyright (c) 2025 Marcus Vinícius de Sá Pereira

Permission is hereby granted, free of charge, to any person obtaining a copy of
this software and associated documentation files (the "Software"), to deal in
the Software without restriction, including without limitation the rights to
use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of
the Software, and to permit persons to whom the Software is furnished to do so,
subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS
FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER
IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
```
