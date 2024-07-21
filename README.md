## ZADANIE REKRUTACYJNE

Aplikacja służy do pobierania listy repozytoriów użytkownika GitHub, które nie są forkami, oraz zwracania odpowiedzi w formacie JSON.

### Wymagania systemowe
- Java 11 lub wyższa
- Maven
- Docker (opcjonalnie)

### Instalacja
1. Sklonuj repozytorium:
    ```bash
    git clone https://github.com/MarcinSz1993/RecruitingTask
    ```
2. Przejdź do katalogu projektu i zbuduj projekt komendą:
    ```bash
    mvn clean install
    ```
3. (Opcjonalnie) Jeśli chcesz użyć Dockera, ściągnij obraz:
    ```bash
    docker pull marcinsz1993/recruiting_task
    ```

### Użycie
1. Uruchom aplikację komendą:
    ```bash
    mvn spring-boot:run
    ```
   Lub jeśli używasz Dockera, uruchom kontener:
    ```bash
    docker run -p 8080:8080 marcinsz1993/recruiting_task
    ```
2. Otwórz przeglądarkę i przejdź do `http://localhost:8080/git/result?username=tuWstawUsername` aby uzyskać listę repozytoriów użytkownika GitHub.

### Przykład odpowiedzi

```json
{
  "repositoryName": "EventManagementKotlin",
  "ownerLogin": "MarcinSz1993",
  "branches": [
    {
      "name": "dev",
      "lastCommitSha": "cb12485ce43b891e32a363b4ea3cf991b76d146f"
    },
    {
      "name": "main",
      "lastCommitSha": "b319598327d2c065dd4acd9f5309374ceeb52492"
    },
    {
      "name": "master",
      "lastCommitSha": "2c2436863db222daf96fca5311349f3f70310d1d"
    }
  ]
}
```
### Obsługa błędów

W przypadku, gdy użytkownik GitHub nie istnieje, aplikacja zwróci odpowiedź o statusie 404 w formacie:

```json
{
  "status": 404,
  "message": "User NieistniejącyUżytkownik does not exist."
}
```
### Licencja
Projekt nie posiada określonej licencji.

Autor projektu: Marcin Szabała