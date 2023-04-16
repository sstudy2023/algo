SELECT car_id, ROUND(AVG(DATEDIFF(END_DATE, START_DATE)+1), 1) as AVERAGE_DURATION
from Car_rental_company_rental_history
group by car_id
having avg(datediff(end_date, start_date) + 1) >= 7
order by AVERAGE_DURATION desc, car_id desc