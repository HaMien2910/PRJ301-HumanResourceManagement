<%-- 
    Document   : modal
    Created on : Feb 27, 2022, 4:45:06 PM
    Author     : PhuongNH
--%>

<!-- Modal Delete-->
<div id="confirmation" class="modal-container">
    <div class="modal">
        <form class="modal-form" method="POST">
            <section>
                <header class="modal--header">
                    <h5><strong>Are you sure you want to delete?</strong></h5>
                </header>
                <section class="modal--content">

                </section>
                <footer class="modal--footer">
                    <div class="footer-contain">
                        <button class="modal-btn" onclick="onCancel()">Cancel</button>
                        <input type="submit" class="modal-btn modal-confirm-btn btn-danger" value="Confirm"/>
                    </div>
                </footer>
            </section>
        </form>
    </div>
</div>