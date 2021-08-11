---
title: How To - How to send E-Mails from static HTML Website
date: 2020-10-16 00:00:00 Z
categories:
- HowTo
tags:
- HowTo
layout: article
cover: /assets/logo/howto.png
sharing: true
license: false
aside:
  toc: true
pageview: true
---

## How to send E-Mails from static HTML Website

In this Example We are going to use Google Spreadsheet

**Login to Google Docs, Go to Spreadsheets**
<https://docs.google.com/spreadsheets/u/0/>

**Make a Copy of this Spreadsheet**
<https://docs.google.com/spreadsheets/d/1Bn4m6iA_Xch1zzhNvo_6CoQWqOAgwwkOWJKC-phHx2Q/copy>  
![](media/f98577b52309ef0368a5d55263affb3a.png)


Open the **Script editor…** by clicking “**Tools**” \> “**Script editor…**”  
![](media/c4404a164946521db816e4504fa323e1.png)

**Place below Code / Change Email-Id of existing code**
```javascript
/******************************************************************************
 * This tutorial is based on the work of Martin Hawksey twitter.com/mhawksey  *
 * But has been simplified and cleaned up to make it more beginner friendly   *
 * All credit still goes to Martin and any issues/complaints/questions to me. *
 ******************************************************************************/

var TO_ADDRESS = "contact.nelsonic@gmail.com"; // where to send form data

function doPost(e) {

  try {
    Logger.log(e); // the Google Script version of console.log see: Class Logger
    MailApp.sendEmail(TO_ADDRESS, "Contact Form Submitted",
                      JSON.stringify(e.parameters));
    // return json success results
    return ContentService
          .createTextOutput(
            JSON.stringify({"result":"success",
                            "data": JSON.stringify(e.parameters) }))
          .setMimeType(ContentService.MimeType.JSON);
  } catch(error) { // if error return this
    Logger.log(error);
    return ContentService
          .createTextOutput(JSON.stringify({"result":"error", "error": e}))
          .setMimeType(ContentService.MimeType.JSON);
  }
}
```


[Code
Github](https://raw.githubusercontent.com/dwyl/learn-to-send-email-via-google-script-html-no-server/1d1c6727f69dec64a6b7f6bd6ff0dd72d0374210/google-script-just-email.js)
  


Change Email Id & Save  
![](media/f9bf009e5391e3bc9392401165caccee.png)


Save & Create Version of script.gs, by **File \> Manage Versions \> Save
Version**  
![](media/6d5fee429607c0095416ea0e553186d2.png)



**Publish the Updated Script.gs as a Web App**  
![20 a-publish](media/4879d52c614d5679489a7cb2d3367fd1.png)

![](media/8a18ff350c6e62b9b794328747396108.png)



Copy URL  
![](media/a555bc8cf8697c66aa51205b63233563.png)

**Create your basic HTML Form**  
Download HTML File :
[Index.html](https://raw.githubusercontent.com/dwyl/learn-to-send-email-via-google-script-html-no-server/master/index.html)

```html
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="contact form example">
  <title>Contact Form Example</title>
</head>

<body>
  <h2 class="content-head is-center">Contact Us!</h2>
  <aside>
       <p>
           We would <em>love</em> to hear from you! </p>
           <p>Please use the <b><em>Contact Form</em></b>
           to send us a message.
       </p>
   </aside>

<!-- START HERE -->
   <link rel="stylesheet" href="https://unpkg.com/purecss@1.0.0/build/pure-min.css">
   <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
   <!-- Style The Contact Form How Ever You Prefer -->
   <link rel="stylesheet" href="style.css">

  <form class="gform pure-form pure-form-stacked" method="POST" data-email="example@email.net"
  action="https://script.google.com/macros/s/AKfycbwMxYDrufp73bKdU8gMvxFDdHRuzcR4IKQUB33B7GqwyfyZS04/exec">
    <!-- change the form action to your script url -->

    <div class="form-elements">
      <fieldset class="pure-group">
        <label for="name">Name: </label>
        <input id="name" name="name" placeholder="What your Mom calls you" />
      </fieldset>

      <fieldset class="pure-group">
        <label for="message">Message: </label>
        <textarea id="message" name="message" rows="10"
        placeholder="Tell us what's on your mind..."></textarea>
      </fieldset>

      <fieldset class="pure-group">
        <label for="email"><em>Your</em> Email Address:</label>
        <input id="email" name="email" type="email" value=""
        required placeholder="your.name@email.com"/>
      </fieldset>

      <fieldset class="pure-group">
        <label for="color">Favourite Color: </label>
        <input id="color" name="color" placeholder="green" />
      </fieldset>

      <fieldset class="pure-group honeypot-field">
        <label for="honeypot">To help avoid spam, utilize a Honeypot technique with a hidden text field; must be empty to submit the form! Otherwise, we assume the user is a spam bot.</label>
        <input id="honeypot" type="text" name="honeypot" value="" />
      </fieldset>

      <button class="button-success pure-button button-xlarge">
        <i class="fa fa-paper-plane"></i>&nbsp;Send</button>
    </div>

    <!-- Customise the Thankyou Message People See when they submit the form: -->
    <div class="thankyou_message" style="display:none;">
      <h2><em>Thanks</em> for contacting us!
        We will get back to you soon!</h2>
    </div>

  </form>

  <!-- Submit the Form to Google Using "AJAX" -->
  <script data-cfasync="false" src="form-submission-handler.js"></script>
<!-- END -->

</body>
</html>
```



### Ref.  
<https://github.com/dwyl/learn-to-send-email-via-google-script-html-no-server>
