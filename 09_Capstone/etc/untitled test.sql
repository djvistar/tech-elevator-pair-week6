SELECT ROW_NUMBER ()OVER (ORDER BY venue.name ASC) AS row_id, venue.*, city.name AS city_name, state.name AS state_name
FROM venue
JOIN city ON venue.city_id = city.id 
JOIN state ON city.state_abbreviation = state.abbreviation 


select venue.id, venue.name, space.id, space.name, space.max_occupancy, reservation.start_date, reservation.end_date, space.open_from, space.open_to
FROM space
Left Outer Join reservation ON space.id = reservation.space_id
Join venue ON venue.id = space.venue_id
				
				
select reservation.*, space.*, venue.id, venue.name, space.id, space.name, space.max_occupancy, reservation.start_date, reservation.end_date, space.open_from, space.open_to
FROM space
Join reservation ON space.id = reservation.space_id
Join venue ON venue.id = space.venue_id


select venue.*, reservation.*, venue.id AS id, venue.name AS venue_name, space.id AS space_id, space.name AS space_name
From space
Join reservation ON space.id = reservation.space_id
Join venue ON venue.id = space.venue_id


        Declare start_date AS DATETIME
       C
	 