<%@ page language="java" contentType="text/html ; charset=utf-8" pageEncoding="UTF-8" %>

  <!DOCTYPE html>
  <html lang="ko">

  <head>
    <meta charset="UTF-8" />
    <link rel="icon" type="image/png" href="/favicon.png" />
    <link rel="stylesheet" type="text/css" href="/css/member/pointChargeTest.css" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>포트원 결제연동 샘플</title>
    <script src="https://cdn.portone.io/v2/browser-sdk.js" async defer></script>
  </head>

  <body>

    <h3>포인트 충전하기</h3>
    <form id="pointForm">
      <label>
        <input type="radio" name="pointCharge" value="1000" /> 1,000원
      </label>
      <label>
        <input type="radio" name="pointCharge" value="5000" /> 5,000원
      </label>
      <label>
        <input type="radio" name="pointCharge" value="10000" /> 10,000원
      </label>
      <br />
    </form>
    <div id="root">
      <main id="checkoutDialog">
        <form id="checkoutForm">
          <article>
            <div class="item">
              <div class="item-image">
                <img id="itemImage" />
              </div>
              <div class="item-text">
                <h5 id="itemName"></h5>
                <p class="price-value"></p>
              </div>
            </div>
            <div class="price">
              <label>총 구입 가격</label>
              <span class="price-value"></span>
            </div>
          </article>
          <button id="checkoutButton" type="submit">결제</button>
        </form>
      </main>
      <dialog id="failDialog">
        <header>
          <h1>결제 실패</h1>
        </header>
        <p id="failMessage"></p>
        <button type="button" class="closeDialog">닫기</button>
      </dialog>
      <dialog id="successDialog">
        <header>
          <h1>결제 성공</h1>
        </header>
        <p>결제에 성공했습니다.</p>
        <button type="button" class="closeDialog">닫기</button>
      </dialog>
      <dialog id="virtualAccountDialog">
        <header>
          <h1>가상계좌 발급 완료</h1>
        </header>
        <p>가상계좌가 발급되었습니다.</p>
        <button type="button" class="closeDialog">닫기</button>
      </dialog>
    </div>


    <script src="/js/member/pointChargeTest.js"></script>
  </body>

  </html>