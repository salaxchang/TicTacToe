<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<html>
    <head>
        <title>Tic Tac Toe</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="css/ttt.css"/>
    </head>
    <body>
        <h1>TicTacToe</h1>

        <c:choose>
            <c:when  test="${game.over}">
                <h2>
                	<c:set var="winner" value = "${game.winner}"/>
                    <c:choose>
                        <c:when test="${empty winner}">
                            Draw Game!
                        </c:when>
                        <c:otherwise>
                            <div class="win">
                                Winner: ${winner}
                            </div>
                        </c:otherwise>
                    </c:choose>
                </h2>   
            </c:when>
            <c:otherwise>
                <div class="gameStatus">
                    Turn : ${game.turn}
                    <br>
                    Current Player : <span class="${game.currentPlayer}">${game.currentPlayer}</span>
                </div>
            </c:otherwise>
        </c:choose> 

        <table>
            <tr>
                <c:forEach var="space" items="${game.grid}" varStatus="stat">
                    <td class="${game.winningSpaces[stat.index] ? 'win': ''}"><c:choose>
                            <c:when test="${empty space}">
                                <form action="setMark">
                                    <input type="hidden" name="space" value="${stat.index}" />
                                    <input class="open" type="submit" value="&nbsp;" name="markBtn" ${game.over ? 'disabled' : ''}/>
                                </form>
                            </c:when>
                            <c:otherwise>
                                <span class="${space}"> ${space}</span>
                            </c:otherwise>
                        </c:choose>
                    </td>
                    <c:if test="${stat.count % 3 == 0 && stat.count < 9}">
                    </tr><tr>
                    </c:if>
                </c:forEach>
            </tr>
            <tr>
                <td colspan="3">
                    <form action="resetGame">
                        <input class="resetBtn" type="submit" value="Restart" name="resetBtn" />
                    </form>
                </td>
            </tr>
        </table>
    </body>
</html>
