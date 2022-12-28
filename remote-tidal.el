;;; remote-tidal.el --- Description -*- lexical-binding: t; -*-
;;
;; Copyright (C) 2022 w1n5t0n
;;
;; Author: w1n5t0n <lm-w1n5t0n@protonmail.com>
;; Maintainer: w1n5t0n <lm-w1n5t0n@protonmail.com>
;; Created: December 26, 2022
;; Modified: December 26, 2022
;; Version: 0.0.1
;; Keywords: abbrev bib c calendar comm convenience data docs emulations extensions faces files frames games hardware help hypermedia i18n internal languages lisp local maint mail matching mouse multimedia news outlines processes terminals tex tools unix vc wp
;; Homepage: https://github.com/w1n5t0n/remote-tidal
;; Package-Requires: ((emacs "24.3"))
;;
;; This file is not part of GNU Emacs.
;;
;;; Commentary:
;;
;;  Description
;;
;;; Code:

;; (require 'osc)
;;
(let ((f "/home/w1n5t0n/src/emacs/osc.el"))
  (if (file-exists-p f)
      (progn
        (load-file f)
        (require 'osc))
    "file doesn't exist"))


;; CLIENT
(defvar osc-client nil "Connection to send OSC From Emacs")

(defun osc-make-client (host port)
  (make-network-process
   :name "OSC client"
   :host host
   :service port
   :type 'datagram
   :family 'ipv4))

(defun osc-connect ()
  (if osc-client   (delete-process osc-client))
  (setq osc-client (osc-make-client "localhost" 4561)))

(osc-connect)

(osc-send-message osc-client "hello/123" 1 "hi")

;; SERVER
(setq my-server
      (osc-make-server
       "localhost" 7770
       (lambda (path &rest; args)
         (message "Unhandled: %s %S" path args)))))

(osc-server-set-handler my-server "/a/b/c/d"
                        (lambda (path &rest args)
                          ;; IMPLEMENT HANDLER HERE
                          nil))

;; (provide 'remote-tidal)
;;; remote-tidal.el ends here
