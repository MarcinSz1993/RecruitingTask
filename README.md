## ZADANIE REKRUTACYJNE

Aplikacja służy do pobierania listy repozytoriów użytkownika GitHub,
które nie są forkami, oraz zwracania odpowiedzi w formacie JSON.

### Wymagania systemowe
- Java 11 lub wyższa
- Maven
- Docker (opcjonalnie)

### Instalacja
1. Sklonuj repozytorium: gti clone https://github.com/RecruitingTask
2. Przejdź do katalogu projektu, a następnie zbuduj projekt komendą: mvn clean install
-----------------------------------------------------
1. Otwórz wiersz poleceń i wpisz: docker pull marcinsz1993/recruiting_task

### Użycie
1. Uruchom apliakcję komendą mvn spring-boot:run lub docker run -p 8080:8080 marcinsz1993/recruiting_task
2. Otwórz przeglądarkę i przejdź do http://localhost:8080/api/repos/{username},
   aby uzyskać listę repozytoriów użytkownika GitHub.

### Przykład odpowiedzi
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

### Obsługa błędów
W przypadku, gdy użytkownik GitHub nie istnieje,
aplikacja zwróci odpowiedź o statusie 404 w formacie:
{
"status": 404,
"message": "User NieistniejącyUżytkownik does not exist."
}


### Licencja
Projekt nie posiada określonej licencji.

### Autor projektu: Marcin Szabała