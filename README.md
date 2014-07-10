AED_1314_TP3

#### Algoritmos e Estruturas de Dados
#### FCTUC - DEI

### Trabalho Prático 3

##### Objectivo
- Estudo de algoritmos de ordenamento


##### Tarefas
- Tarefa A - Ordenamento por inserção
- Tarefa B - Ordenamento por chave - Quick Sort com melhoramentos
- Tarefa C - Ordenamento por base - Radix Sort LSD


##### Enunciado
A empresa NovasRotas Lda dedica-se ao desenvolvimento de software para gestão de frotas de veículos. O software utilizado envolve a existência de equipamento de localização a bordo das viaturas, das quais são recebidos registos (pontos recolhidos pelo receptor de GPS) com os seguintes campos:
- Identificador do registo (“integer”)
- Identificador da viatura (“integer”)
- “timestamp” (AAAA-MM-DD HH:MM:SS)
- latitude (“float”)
- longitude (“float”)

A seguir apresenta-se uma amostra dos registos recebidos, em que a primeira linha contém o nome das colunas (os ficheiros de entrada de teste não contêm a linha de cabeçalho):

id,id_vehicle,date,latitude,longitude
1245619,879,2009-12-09 00:00:47,38.832,-9.09474
1245618,320,2009-12-09 00:00:53,38.7401,-9.22841
1245617,720,2009-12-09 00:00:55,38.7206,-9.12267

**_O Problema_**

A empresa pretende identificar as zonas onde há maior passagem de veículos. Uma zona é definida por todas as latitudes/longitudes com o mesmo par de valores latitude/longitude
truncados ao nível da centésima de grau.
Neste sentido deve desenvolver um programa que faça a listagem das zonas seguida do número de pontos encontrados nessa zona. A listagem deve compreender as zonas organizadas por ordem crescente, primeiro de latitude e depois de longitude.

**_A Abordagem_**

Para este problema, a abordagem que se pretende passa por dois passos:
1. Ordenamento de todos os registos por latitude seguida de longitude (ambas expressas em
graus e truncadas ao nível da centésima);
2. Varrimento da lista ordenada para contagem do número de pontos associados a cada par
latitude/longitude.
Nota: as colunas com identificador do registo; identificador da viatura e “timestamp” (AAAAMM-DD HH:MM:SS) não vão ser utilizadas.

Nas tarefas de ordenamento:
- Para as medições de tempo deve adoptar soluções que minimizem a contaminação do tempo de execução pelo tempo de leitura e escrita dos dados, medindo dentro do possível só os tempos de ordenamento. Se em alguma situação tal não for possível deve ser identificada.
- A formação da chave de ordenamento dos registos de entrada deve ter em conta o método de ordenamento utilizado.


##### Testes
**_Input_**

O input é composto por uma linha com o número de pontos a considerar. Seguido das linhas com os registos dos pontos GPS produzidos pelos veículos.

**_Output_**

O output deverá compreender um conjunto de linhas, cada linha com a definição de cada zona e do número de pontos GPS detectados nessa zona ao longo de todo o ficheiro de input.
A definição de cada zona é feita em termos de latitude e longitude em graus e truncada na centésima de grau.
As zonas devem ser apresentadas por ordem crescente, primeiro de latitude e depois de longitude.

Nota: tanto a última linha de input como de output terminam com o símbolo de mudança de
linha.
