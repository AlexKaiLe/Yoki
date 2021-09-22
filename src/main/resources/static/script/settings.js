/**
 * Creates the logout div which will redirect user to the login page
 */
function login() {
    const pop = document.getElementById("login");
    const popp = document.getElementById("popup");
    if (pop !== null) {
        pop.remove();
    } else if (popp !== null) {
        popp.remove();
    }

    const login = document.createElement("div");
    login.class = "options";
    login.id = "login";

    const name = document.createElement("h3");
    name.innerHTML = "Are you sure you want to logout?";
    name.id = "logout"

    const buttons = document.createElement("div");
    buttons.id = "buttonDiv";
    const yes = document.createElement("button");
    yes.id = "match-button";
    yes.className = "yes";
    yes.innerHTML = "Yes";
    yes.onclick = function () {
        logout();
    };
    const aLink = document.createElement("a");
    aLink.href = "/yoki";

    document.getElementById("main").appendChild(login);
    login.appendChild(name);
    login.appendChild(aLink);
    aLink.append(buttons);
    buttons.appendChild(yes);
}

/**
 * Creates the light mode dark mode toggle div
 */
function lightDark() {
    const pop = document.getElementById("login");
    const popp = document.getElementById("popup");
    if (pop !== null) {
        pop.remove();
    } else if (popp !== null) {
        popp.remove();
    }

    const login = document.createElement("div");
    login.class = "options";
    login.id = "login";

    const buttons = document.createElement("div");
    buttons.id = "buttonDiv";
    const light = document.createElement("button");
    light.id = "match-button";
    light.className = "LD";
    light.innerHTML = "Light";
    light.onclick = function () {
        lightMode();
    };

    const dark = document.createElement("button");
    dark.id = "match-button";
    dark.innerHTML = "Dark";
    dark.className = "LD";
    dark.onclick = function () {
        darkMode();
    };

    document.getElementById("main").appendChild(login);
    login.appendChild(buttons);
    buttons.appendChild(light);
    buttons.appendChild(dark);
}

/**
 * Creates the terms and conditions div
 */
function termsConditions() {
    const pop = document.getElementById("login");
    const popp = document.getElementById("popup");
    if (pop !== null) {
        pop.remove();
    } else if (popp !== null) {
        popp.remove();
    }

    const popup = document.createElement("div");
    popup.id = "popup";
    popup.className = "popup";
    popup.innerText = "Agreement between User and http://localhost:4567/yoki\n" +
        "Welcome to http://localhost:4567/yoki . The http://localhost:4567/yoki website (the \"Site\") is comprised of various web pages operated by Yoki (\" Yoki \"). The http://localhost:4567/yoki is offered to you conditioned on your acceptance without modification of the terms, conditions,\n" +
        "and notices contained herein (the \"Terms\"). Your use of http://localhost:4567/yoki constitutes your agreement to all such Terms. Please read these terms carefully, and keep a copy of them for your reference.\n" +
        "\n" +
        "http://localhost:4567/yoki is a Social Networking Site.\n" +
        "\n" +
        "Yoki is a platform on which people can connect with strangers with similar interests and hobbies. As a result of this pandemic, it has been hard to build meaningful connections with others. Bridging the gap between Facebook(virtual connections) and Omegle (meeting strangers) to build lasting relationships. Similar to datamatch, where each person contains attributes/interests where we can create edges between the people. Use the tree to find nearest neighbors/page rank. Create a mathematical formula to make sure that people are accurately matched with others.\n" +
        "\n" +
        "Privacy\n" +
        "Your use of http://localhost:4567/yoki is subject to Yoki 's Privacy Policy. Please review our Privacy Policy, which also governs the Site and informs users of our data collection practices.\n" +
        "\n" +
        "Electronic Communications\n" +
        "Visiting http://localhost:4567/yoki or sending emails to Yoki constitutes electronic communications. You consent to receive electronic communications and you agree that all agreements, notices, disclosures and other communications that we provide to you electronically, via email and on the Site, satisfy any legal requirement that such communications be in writing.\n" +
        "\n" +
        "Your Account\n" +
        "If you use this site, you are responsible for maintaining the confidentiality of your account and password and for restricting access to your computer, and you agree to accept responsibility for all activities that occur under your account or password. You may not assign or otherwise transfer your account to any other person or entity. You acknowledge that Yoki is not responsible for third party access to your account that results from theft or misappropriation of your account. Yoki and its associates reserve the right to refuse or cancel service, terminate accounts, or remove or edit content in our sole discretion.\n" +
        "\n" +
        "Children Under Thirteen\n" +
        "Yoki does not knowingly collect, either online or offline, personal information from persons under the age of thirteen. If you are under 18, you may use http://localhost:4567/yoki only with permission of a parent or guardian.\n" +
        "\n" +
        "Links to Third Party Sites/Third Party Services\n" +
        "http://localhost:4567/yoki may contain links to other websites (\"Linked Sites\"). The Linked Sites are not under the control of Yoki and Yoki is not responsible for the contents of any Linked Site, including without limitation any link contained in a Linked Site, or any changes or updates\n" +
        "to a Linked Site. Yoki is providing these links to you only as a convenience, and the inclusion of any link does not imply endorsement by Yoki of the site or any association with its operators.\n" +
        "\n" +
        "Certain services made available via http://localhost:4567/yoki are delivered by third party sites and organizations. By using any product, service or functionality originating from the http://localhost:4567/yoki domain, you hereby acknowledge and consent that Yoki may share such information and data with any third party with whom Yoki has a contractual relationship to provide the requested product, service or functionality on behalf of http://localhost:4567/yoki users and customers.\n" +
        "\n" +
        "No Unlawful or Prohibited Use/Intellectual Property\n" +
        "You are granted a non-exclusive, non-transferable, revocable license to access and use http://localhost:4567/yoki strictly in accordance with these terms of use. As a condition of your use of the Site, you warrant to Yoki that you will not use the Site for any purpose that is unlawful or prohibited by these Terms. You may not use the Site in any manner which could damage, disable, overburden, or impair the Site or interfere with any other party's use and enjoyment of the Site. \n" +
        "\n" +
        "You may not obtain or attempt to obtain any materials or information through any means not\n" +
        "intentionally made available or provided for through the Site. All content included as part of the Service, such as text, graphics, logos, images, as well as the compilation thereof, and any software used on the Site, is the property of Yoki or its suppliers and protected by copyright and other laws that protect intellectual property and proprietary rights. You agree to observe and abide by all copyright and other proprietary notices, legends or other restrictions contained in any such content and will not make any changes thereto.\n" +
        "\n" +
        "You will not modify, publish, transmit, reverse engineer, participate in the transfer or sale, create derivative works, or in any way exploit any of the content, in whole or in part, found on the Site. Yoki content is not for resale. Your use of the Site does not entitle you to make any unauthorized use of any protected content, and in particular you will not delete or alter any proprietary rights or attribution notices in any content. You will use protected content solely for your personal use, and will make no other use of the content without the express written permission of\n" +
        "Yoki and the copyright owner. You agree that you do not acquire any ownership rights in any protected content. We do not grant you any licenses, express or implied, to the intellectual property of Yoki or our licensors except as expressly authorized by these Terms.\n" +
        "\n" +
        "Use of Communication Services\n" +
        "The Site may contain bulletin board services, chat areas, news groups, forums, communities, personal web pages, calendars, and/or other message or communication facilities designed to enable you to communicate with the public at large or with a group (collectively, \"Communication Services\"). You agree to use the Communication Services only to post, send and receive messages and material that are proper and related to the particular Communication Service.\n" +
        "\n" +
        "By way of example, and not as a limitation, you agree that when using a Communication Service, you will not: defame, abuse, harass, stalk, threaten or otherwise violate the legal rights (such as rights of privacy and publicity) of others; publish, post, upload, distribute or disseminate any inappropriate, profane, defamatory, infringing, obscene, indecent or unlawful topic, name, material or information; upload files that contain software or other material protected by intellectual property laws (or by rights of privacy of publicity) unless you own or control the rights thereto or have received all necessary consents; upload files that contain viruses, corrupted files, or any other similar software or programs that may damage the operation of another's computer, advertise or offer to sell or buy any goods or services for any business purpose, unless such Communication Service specifically allows such messages; conduct or forward surveys, contests, pyramid schemes or chain letters, download any file posted by another user of a Communication Service that you know, or reasonably should know, cannot be legally distributed in such manner; falsify or delete any author attributions, legal or other proper notices or proprietary designations or labels of the origin or source of software or other material contained in a file that is uploaded; restrict or inhibit any other user from using and enjoying the Communication Services; violate any code of conduct or other guidelines which may be applicable for any particular Communication Service; harvest or otherwise collect\n" +
        "information about others, including e-mail addresses, without their consent; violate any applicable laws or regulations.\n" +
        "\n" +
        "Yoki has no obligation to monitor the Communication Services. However, Yoki reserves the right to review materials posted to a Communication Service and to remove any materials in its sole discretion. Yoki reserves the right to terminate your access to any or all of the Communication Services at any time without notice for any reason whatsoever. \n" +
        "\n" +
        "Yoki reserves the right at all times to disclose any information as necessary to satisfy any applicable law, regulation, legal process or governmental request, or to edit, refuse to post or to remove any information or materials, in whole or in part, in Yoki 's sole discretion. \n" +
        "\n" +
        "Always use caution when giving out any personally identifying information about yourself or your children in any Communication Service. Yoki does not control or endorse the content, messages or information found in any Communication Service and, therefore, Yoki specifically disclaims any liability with regard to the Communication Services and any actions resulting from your participation in any Communication Service. Managers and hosts are not authorized Yoki spokespersons, and their views do not necessarily reflect those of Yoki .\n" +
        " \n" +
        "Materials uploaded to a Communication Service may be subject to posted limitations on usage, reproduction and/or dissemination. You are responsible for adhering to such limitations if you upload the materials. \n" +
        "\n" +
        "Materials Provided to http://localhost:4567/yoki Posted on Any Yoki Web Page\n" +
        "Yoki does not claim ownership of the materials you provide to http://localhost:4567/yoki (including feedback and suggestions) or post, upload, input or submit to any Yoki Site or our associated services (collectively \"Submissions\"). However, by posting, uploading, inputting,\n" +
        "providing or submitting your Submission you are granting Yoki , our affiliated companies and necessary sublicensees permission to use your Submission in connection with the operation of their Internet businesses including, without limitation, the rights to: copy, distribute, transmit,\n" +
        "publicly display, publicly perform, reproduce, edit, translate and reformat your Submission; and to publish your name in connection with your\n" +
        "Submission.\n" +
        "\n" +
        "No compensation will be paid with respect to the use of your Submission, as provided herein. Yoki is under no obligation to post or use any Submission you may provide and may remove any Submission at any time in Yoki 's sole discretion.\n" +
        "\n" +
        "By posting, uploading, inputting, providing or submitting your Submission you warrant and represent that you own or otherwise control all of the rights to your Submission as described in this section including, without limitation, all the rights necessary for you to provide, post, upload, input or submit the Submissions.\n" +
        "\n" +
        "Third Party Accounts\n" +
        "You will be able to connect your Yoki account to third party accounts. By connecting your Yoki account to your third party account, you acknowledge and agree that you are consenting to the continuous release of information about you to others (in accordance with your privacy settings on those third party sites). If you do not want information about you to be shared in this manner, do not use this feature.\n" +
        "\n" +
        "International Users\n" +
        "The Service is controlled, operated and administered by Yoki from our offices within the USA. If you access the Service from a location outside the USA, you are responsible for compliance with all local laws. You agree that you will not use the Yoki Content accessed through http://localhost:4567/yoki in any country or in any manner prohibited by any applicable laws, restrictions or regulations.\n" +
        "\n" +
        "Indemnification\n" +
        "You agree to indemnify, defend and hold harmless Yoki , its officers, directors, employees, agents and third parties, for any losses, costs, liabilities and expenses (including reasonable attorney's fees) relating to or arising out of your use of or inability to use the Site or services, any user postings made by you, your violation of any terms of this Agreement or your violation of any rights of a third party, or your violation of any applicable laws, rules or regulations. Yoki reserves the right, at its own cost, to assume the exclusive defense and control of any matter\n" +
        "otherwise subject to indemnification by you, in which event you will fully cooperate with Yoki in asserting any available defenses.\n" +
        "\n" +
        "Arbitration\n" +
        "In the event the parties are not able to resolve any dispute between them arising out of or concerning these Terms and Conditions, or any provisions hereof, whether in contract, tort, or otherwise at law or in equity for damages or any other relief, then such dispute shall be resolved\n" +
        "only by final and binding arbitration pursuant to the Federal Arbitration Act, conducted by a single neutral arbitrator and administered by the American Arbitration Association, or a similar arbitration service selected by the parties, in a location mutually agreed upon by the parties. The\n" +
        "arbitrator's award shall be final, and judgment may be entered upon it in any court having jurisdiction. In the event that any legal or equitable action, proceeding or arbitration arises out of or concerns these Terms and Conditions, the prevailing party shall be entitled to recover its costs and reasonable attorney's fees. The parties agree to arbitrate all disputes and claims in regards to these Terms and Conditions or any disputes arising as a result of these Terms and Conditions, whether directly or indirectly, including Tort claims that are a result of these Terms and Conditions. The parties agree that the Federal Arbitration Act governs the interpretation and enforcement of this provision. The entire dispute, including the scope and enforceability of this arbitration provision shall be determined by the Arbitrator. This arbitration provision shall survive the termination of these Terms and Conditions.\n" +
        "\n" +
        "Liability Disclaimer\n" +
        "THE INFORMATION, SOFTWARE, PRODUCTS, AND SERVICES INCLUDED IN OR AVAILABLE THROUGH THE SITE MAY INCLUDE INACCURACIES OR TYPOGRAPHICAL ERRORS. CHANGES ARE PERIODICALLY ADDED TO THE INFORMATION HEREIN. YOKI AND/OR ITS SUPPLIERS MAY MAKE IMPROVEMENTS AND/OR CHANGES IN THE SITE AT ANY TIME. \n" +
        "\n" +
        "YOKI AND/OR ITS SUPPLIERS MAKE NO REPRESENTATIONS ABOUT THE SUITABILITY, RELIABILITY, AVAILABILITY, TIMELINESS, AND ACCURACY OF THE INFORMATION, SOFTWARE, PRODUCTS, SERVICES AND RELATED GRAPHICS CONTAINED ON THE SITE FOR ANY PURPOSE. TO THE MAXIMUM EXTENT PERMITTED BY APPLICABLE LAW, ALL SUCH INFORMATION, SOFTWARE, PRODUCTS, SERVICES AND RELATED GRAPHICS ARE PROVIDED \"AS IS\" WITHOUT WARRANTY OR CONDITION OF ANY KIND. YOKI AND/OR ITS SUPPLIERS HEREBY DISCLAIM ALL WARRANTIES AND CONDITIONS WITH REGARD TO THIS INFORMATION, SOFTWARE, PRODUCTS, SERVICES AND RELATED GRAPHICS, INCLUDING ALL IMPLIED WARRANTIES OR CONDITIONS OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE, TITLE AND NON- INFRINGEMENT.\n" +
        "\n" +
        "TO THE MAXIMUM EXTENT PERMITTED BY APPLICABLE LAW, IN NO EVENT SHALL YOKI AND/OR ITS SUPPLIERS BE LIABLE FOR ANY DIRECT, INDIRECT, PUNITIVE, INCIDENTAL, SPECIAL, CONSEQUENTIAL DAMAGES OR ANY DAMAGES WHATSOEVER INCLUDING, WITHOUT LIMITATION, DAMAGES FOR LOSS OF USE, DATA OR PROFITS, ARISING OUT OF OR IN ANY WAY CONNECTED WITH THE USE OR PERFORMANCE OF THE SITE, WITH THE DELAY OR INABILITY TO USE THE SITE OR RELATED SERVICES, THE PROVISION OF OR FAILURE TO PROVIDE SERVICES, OR FOR ANY INFORMATION, SOFTWARE, PRODUCTS, SERVICES AND RELATED GRAPHICS OBTAINED THROUGH THE SITE, OR OTHERWISE ARISING OUT OF THE USE OF THE SITE, WHETHER BASED ON CONTRACT, TORT, NEGLIGENCE, STRICT LIABILITY OR OTHERWISE, EVEN IF YOKI OR ANY OF ITS SUPPLIERS HAS BEEN ADVISED OF THE POSSIBILITY OF DAMAGES. BECAUSE SOME STATES/JURISDICTIONS DO NOT ALLOW THE EXCLUSION OR LIMITATION OF LIABILITY FOR CONSEQUENTIAL OR INCIDENTAL DAMAGES, THE ABOVE LIMITATION MAY NOT APPLY TO YOU. IF YOU ARE DISSATISFIED WITH ANY PORTION OF THE SITE, OR WITH ANY OF THESE TERMS OF USE, YOUR SOLE AND EXCLUSIVE REMEDY IS TO DISCONTINUE USING THE SITE.\n" +
        "\n" +
        "Termination Access Restriction\n" +
        "Yoki reserves the right, in its sole discretion, to terminate your access to the Site and the related services or any portion thereof at any time, without notice. To the maximum extent permitted by law, this agreement is governed by the laws of the State of California and you hereby consent to the exclusive jurisdiction and venue of courts in California in all disputes arising out of or relating to the use of the Site. Use of the Site is unauthorized in any jurisdiction that does not give effect to all provisions of these Terms, including, without limitation, this section. \n" +
        "\n" +
        "You agree that no joint venture, partnership, employment, or agency relationship exists between you and Yoki as a result of this agreement or use of the Site. Yoki 's performance of this agreement is subject to existing laws and legal process, and nothing contained in this agreement is in derogation of Yoki 's right to comply with governmental, court and law enforcement requests or requirements relating to your use of the Site or information provided to or gathered by Yoki with respect to such use. If any part of this agreement is determined to be invalid or unenforceable pursuant to applicable law including, but not limited to, the warranty disclaimers and liability limitations set forth above, then the invalid or unenforceable provision will be deemed superseded by a valid, enforceable provision that most closely matches the intent of the original provision and the remainder of the agreement shall continue in effect.\n" +
        "\n" +
        "Unless otherwise specified herein, this agreement constitutes the entire agreement between the user and Yoki with respect to the Site and it supersedes all prior or contemporaneous communications and proposals, whether electronic, oral or written, between the user and Yoki with respect to the Site. A printed version of this agreement and of any notice given in electronic form shall be admissible in judicial or administrative proceedings based upon or relating to this agreement to the same extent and subject to the same conditions as other business documents and records originally generated and maintained in printed form. It is the express wish to the parties that this agreement and all related documents be written in English.\n" +
        "\n" +
        "Changes to Terms\n" +
        "Yoki reserves the right, in its sole discretion, to change the Terms under which http://localhost:4567/yoki is offered. The most current version of the Terms will supersede all previous versions. Yoki encourages you to periodically review the Terms to stay informed of our updates.\n" +
        "\n" +
        "Contact Us\n" +
        "Yoki welcomes your questions or comments regarding the Terms:\n" +
        "Yoki\n" +
        "California\n" +
        "Email Address: alexander_li@brown.edu\n" +
        "Telephone number: (425)-698-7955\n" +
        "Effective as of April 20, 2021\n" +
        "\n";
    document.getElementById("main").appendChild(popup);

}

/**
 * Creates the report div
 */
function report() {
    const pop = document.getElementById("login");
    const popp = document.getElementById("popup");
    if (pop !== null) {
        pop.remove();
    } else if (popp !== null) {
        popp.remove();
    }

    const popup = document.createElement("div");
    popup.id = "popup";

    const emailDiv = document.createElement("div");
    emailDiv.id = "nameDiv"
    const email = document.createElement("h3");
    email.innerHTML = "User's Email: ";
    const emailInput = document.createElement("input");
    emailInput.className = "inputs";
    emailInput.id = "report-email";

    const reasonDiv = document.createElement("div");
    reasonDiv.id = "reasonDiv";
    const reason = document.createElement("h3");
    reason.innerHTML = "Reason: ";
    const reasonInput = document.createElement("textarea");
    reasonInput.id = "reasonInput";
    reasonInput.className = "inputs";

    const errMsg = document.createElement("div")
    errMsg.innerText = "No user with that email!"
    errMsg.id = "report-status"

    const reportButton = document.createElement("button");
    reportButton.innerHTML = "Report";
    reportButton.id = "match-button";
    reportButton.className = "reportButton"
    reportButton.onclick = function () {
        sendReport();
    };

    document.getElementById("main").appendChild(popup);
    popup.appendChild(emailDiv);
    emailDiv.appendChild(email);
    emailDiv.appendChild(emailInput);
    popup.appendChild(reasonDiv);
    reasonDiv.appendChild(reason);
    reasonDiv.appendChild(reasonInput);
    popup.append(errMsg)
    popup.appendChild(reportButton);
}

/**
 * Posts to the backend a report
 */
function sendReport() {
    const postParameters = {
        email: document.getElementById("report-email").value,
        report: document.getElementById("reasonInput").value
    };
    fetch('http://localhost:4567/report', {
        method: 'post',
        body: JSON.stringify(postParameters),
        headers: {
            'Content-type': 'application/json; charset=UTF-8',
        },
    })
        .then((response) =>
            response.json())
        .then((data) => {
            let reportStatus = document.getElementById("report-status");
            reportStatus.style.opacity = 100;
            if (data.reported == "true") {
                reportStatus.style.color = "#C97800";
                reportStatus.innerText = "Report sent!";
            } else {
                reportStatus.style.color = "#b50f0f";
                reportStatus.innerText = "No user with that email!";
            }
        })
        .catch(function (error) {
            console.log(error);
        });
}
