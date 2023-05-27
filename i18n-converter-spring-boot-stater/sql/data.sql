INSERT INTO mydms.i18n (id, zh_tw, en_us, zh_cn) VALUES ('label.close', '關閉', 'close', '關閉');
INSERT INTO mydms.i18n (id, zh_tw, en_us, zh_cn) VALUES ('label.knowledge', '知識單元', 'knowledge unit', '知识单元');
INSERT INTO mydms.i18n (id, zh_tw, en_us, zh_cn) VALUES ('message.knowledge.share', '分享知識', 'share knowledge', '分享知识');
INSERT INTO mydms.i18n (id, zh_tw, en_us, zh_cn) VALUES ('message.please.close', '點擊關閉視窗', 'click to close window', '点击关闭视窗
');


INSERT INTO mydms.tag_config (id, many, label, ui_type, select_cate, parent_select_cate, tag_key, aspect_data_category, placeholder) VALUES (1, 1, 'label.close', 'label', 'a', 1, '2', 'a', 'message.please.close');
INSERT INTO mydms.tag_config (id, many, label, ui_type, select_cate, parent_select_cate, tag_key, aspect_data_category, placeholder) VALUES (2, null, 'label.knowledge', 'label', 'b', 1, '2', 'a', 'message.knowledge.share');


INSERT INTO mydms.v_user (uid, account, name, status) VALUES ('UnOT', 'lance.huang', 'lance huang 黃士修', 1);
