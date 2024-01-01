DELIMITER //

CREATE EVENT update_token_of_day
    ON SCHEDULE EVERY 1 DAY
    STARTS TIMESTAMP(CURRENT_DATE, '00:00:00')
DO
BEGIN
UPDATE request_manager rm
SET rm.number_choice = 0
WHERE rm.token_status = 'Rejected';
END//

CREATE EVENT update_token_of_month
    ON SCHEDULE EVERY 1 MONTH
    STARTS TIMESTAMP(CURRENT_DATE, '00:00:00')
DO
BEGIN
UPDATE request_manager rm
SET rm.number_choice = 0
WHERE rm.token_status = 'Deleted';
END//

DELIMITER ;