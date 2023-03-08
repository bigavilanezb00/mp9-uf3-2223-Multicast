## TCP

>**Tasca 1**  
>Implementeu el joc d'endevinar un número que ja hem fet amb UDP però ara amb TCP  
>Amb aquest canvi:
> > - El client i el servidor es parlen només amb Strings, per això la classe SecretNum té el mètode comprova sobrecarregat per usar Strings.

>**Tasca 2**  
>L'Objecte [Llista.java](/src/mp9/uf3/tcp/exemples/Llista.java) consta d'un __nom__(String) i una __llista de numeros__(List -Integer-)
> > - Implementeu un servidor TCP perquè accepti diferents clients alhora que li enviaran un objcte de tipus
> Llista i aquest el hi retornarà el mateix objecte amb el números ordenats i sense repetits.  
> > - Implementeu un client amb TCP que envii al servidor un Llista omplert amb un nom i una llista de números,
> > - i preparat per rebre un objecte del mateix tipus però que el servidor li haurà eliminat els repetits i els números estaran
> > - ordenats.