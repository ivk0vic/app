Aplikacija za dodavanje studenata i cuvanje njihovih podataka u bazi.
Komponente koje su koriscene:
1. Ring - biblioteka za rukovanje HTTP zahtevima
2. Compojure - povezan sa Ring-om, koristimo ga za kreiranje web stranica
3. Hiccup -  sluzi nam da pretvaramo Clojure strukture podataka u HTML kod
4. H2 - mala Java Sql baza za cuvanje podataka
5. Leiningen - alat za upravljanje Clojure jezika

Postupak:
1. Kreiranje projekta uz pomoc Lein komande
2. Dodavanje :dependencies u project.clj
3. Postavka baze uz pomoc lein repl komande
4. Dodavanje ruta u handler.clj
* ovde preciziranmo koje stranice ce sadrzati nasa app preko funkcija
5. Kreiranje fajla views.clj
* implementacija svake funkcije koje smo napisali u handler.clj
* na ovaj nacin sve fukncije vracaju odredjeni html kod
6. Kreiranje funkcija za pristup bazi
* funkcije nam omogucavaju da kad dodamo studenta, on se cuva u bazi
