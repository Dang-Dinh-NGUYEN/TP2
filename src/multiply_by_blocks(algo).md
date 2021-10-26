
* L2-informatique(Aix-Montperrin)
* TP2-Structures Discrètes
* Groupe 2.1
* NGUYEN Dang Dinh
* NGUYEN Duc Duong
* (2021-2022)
* github: https://github.com/Dang-Dinh-NGUYEN/TP2.git


En donnant un algorithme récursif permettant de calculer le produit de deux matrices carrées de même dimension, nous voudrions montrer un exemple comme ci-dessous:

quickProduit:   
entrée:     
        A une matrice carrée de dimension N (N est une puissance de 2).     
        B une matrice carrée de dimension N (N est une puissance de 2).     
sortie: produit de deux matrices. 

début:  
        C = une nouveau matrice carrée de dimension N.   
        si (N == 1) alors retourner C = A.B     
        sinon:       
        C11 = quickProduit(A11,B11) + quickProduit(A12,B21)  
        C12 = quickProduit(A11,B12) + quickProduit(A12,B22)         
        C21 = quickProduit(A21,B11) + quickProduit(A22,B21)     
        C22 = quickProduit(A21,B12) + quickProduit(A22,B22)     
retourner C     
fin

D'où:
[C11 C12][C21 C22] = [A11 A12][A21 A22] * [B11 B12][B21 B22]